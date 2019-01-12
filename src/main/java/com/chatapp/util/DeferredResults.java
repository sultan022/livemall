package com.chatapp.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.springframework.web.context.request.async.DeferredResult;

public final class DeferredResults {
	public static <T> DeferredResult<T> from(CompletableFuture<T> future) {
		DeferredResult<T> deferred = new DeferredResult();
		future.thenAccept(deferred::setResult);
		future.exceptionally((ex) -> {
			if (ex instanceof CompletionException) {
				deferred.setErrorResult(ex.getCause());
			} else {
				deferred.setErrorResult(ex);
			}

			return null;
		});
		return deferred;
	}

	public DeferredResults() {
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else {
			return o instanceof DeferredResults;
		}
	}

	public int hashCode() {
		int result = 1;
		return result;
	}

	public String toString() {
		return "DeferredResults()";
	}
}