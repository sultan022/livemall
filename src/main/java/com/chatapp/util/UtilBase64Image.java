package com.chatapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class UtilBase64Image {
	public static String getImageFromDirectory(String imagePath) {
	 
		
		File file = new File(imagePath);
	    try (FileInputStream imageInFile = new FileInputStream(file)) {
	        // Reading a Image file from file system
	    	String base64Image = "";
	        byte imageData[] = new byte[(int) file.length()];
	        imageInFile.read(imageData);
	        base64Image = Base64.getEncoder().encodeToString(imageData);
	        return base64Image;
	    } catch (FileNotFoundException e) {
	        System.out.println("Image not found" + e);
	    } catch (IOException ioe) {
	        System.out.println("Exception while reading the Image " + ioe);
	    }
	    return null;
	}
	
	public static String saveBase64StringAsImage(String base64Image, String path) {
	   
		createDirectory(path);
		
		try (FileOutputStream imageOutFile = new FileOutputStream(path+"/"+path+"profilepic.png")) {
	        // Converting a Base64 String into Image byte array
	        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
	        imageOutFile.write(imageByteArray);
	    } catch (FileNotFoundException e) {
	        System.out.println("Image not found" + e);
	    } catch (IOException ioe) {
	        System.out.println("Exception while reading the Image " + ioe);
	    }
		
		return path+"/"+path+"profilepic.png";
	}
	
	public static String saveBase64StringAsImageForProduct(String base64Image, String path, int picNo, String userEmail) {
		   
		createDirectory(userEmail+"/"+path);
		
		try (FileOutputStream imageOutFile = new FileOutputStream(userEmail+"/"+path+"/"+path+picNo+".png")) {
	        // Converting a Base64 String into Image byte array
	        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
	        imageOutFile.write(imageByteArray);
	    } catch (FileNotFoundException e) {
	        System.out.println("Image not found" + e);
	    } catch (IOException ioe) {
	        System.out.println("Exception while reading the Image " + ioe);
	    }
		
		return userEmail+"/"+path+"/"+path+picNo+".png";
	}

	public static String saveBase64StringAsMainImageForProduct(String base64Image, String path, String picName, String userEmail) {
		   
		createDirectory(userEmail+"/"+path);
		
		try (FileOutputStream imageOutFile = new FileOutputStream(userEmail+"/"+path+"/"+path+picName+".png")) {
	        // Converting a Base64 String into Image byte array
	        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
	        imageOutFile.write(imageByteArray);
	    } catch (FileNotFoundException e) {
	        System.out.println("Image not found" + e);
	    } catch (IOException ioe) {
	        System.out.println("Exception while reading the Image " + ioe);
	    }
		
		return userEmail+"/"+path+"/"+path+picName+".png";
	}
	
	

	public static void createDirectory(String path) {

		File theDir = new File(path);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		
	}

	public static void removeFile(String path) {

		File file = new File(path);
		
		if(file.exists()){
			if(file.delete())
			{
				System.out.println("File deleted successfully"); 
			} else
	        { 
	            System.out.println("Failed to delete the file"); 
	        } 
			
			
		}
		
	}
	
	
}