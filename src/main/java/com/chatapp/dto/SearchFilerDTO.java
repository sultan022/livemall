package com.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data @AllArgsConstructor
public class SearchFilerDTO {

    List<String> filters;
}
