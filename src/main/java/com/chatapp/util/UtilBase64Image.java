package com.chatapp.util;

import java.io.*;
import java.nio.file.Paths;
import java.util.Base64;

public class UtilBase64Image {


  private static String mainPath ="/live-mall-data/";

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

        try (FileOutputStream imageOutFile = new FileOutputStream(mainPath + path + "/" + path + "profilepic.png")) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }

        return mainPath + path + "/" + path + "profilepic.png";
    }

    public static String saveCategoryIconInDirectory(String base64Image, String path, String channel) {

        if(channel.equalsIgnoreCase("ios"))
            path= "categories/ios/"+path;
        else if (channel.equalsIgnoreCase("android"))
            path= "categories/android/"+path;

        createDirectory(path);

        try (FileOutputStream imageOutFile = new FileOutputStream(mainPath + path + "/" + "icon.png")) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }

        return mainPath + path + "/" + "icon.png";
    }


    public static String saveBase64StringAsImageForProduct(String base64Image, String path, int picNo, String userEmail) {

        createDirectory(userEmail + "/" + path);

        try (FileOutputStream imageOutFile = new FileOutputStream(mainPath + userEmail + "/" + path + "/" + path + picNo + ".png")) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }

        return mainPath+ userEmail + "/" + path + "/" + path + picNo + ".png";
    }

    public static String saveBase64StringAsMainImageForProduct(String base64Image, String path, String picName, String userEmail) {

        createDirectory(userEmail + "/" + path);

        try (FileOutputStream imageOutFile = new FileOutputStream(mainPath + userEmail + "/" + path + "/" + path + picName + ".png")) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }

        return mainPath + userEmail + "/" + path + "/" + path + picName + ".png";
    }


    public static void createDirectory(String path) {

       if(UtilBase64Image.createMainDirectory()){

           String filePath = Paths.get(mainPath+path).toString();


           File theDir = new File(filePath);
           boolean result = false;

           // if the directory does not exist, create it
           if (!theDir.exists()) {

               try {
                   System.out.println("creating directory: "+theDir.getPath());


                   result=  theDir.mkdirs();
               } catch (Exception se) {
                   //handle it
               }
               if (result) {
                   System.out.println("DIR created");
               }
           }
       }



    }

    public static boolean createMainDirectory(){

        String filePath = Paths.get(mainPath).toString();

        File theDir = new File(filePath);
        boolean result = false;

        // if the directory does not exist, create it
        if (!theDir.exists()) {

            try {
                System.out.println("creating directory: "+theDir.getPath());


                theDir.mkdirs();
                result=true;
            } catch (Exception se) {
               se.printStackTrace();
            }
            if (result) {
                System.out.println("DIR created");
            }
        }else return true;

        return result;
    }

    public static void removeFile(String path) {

        File file = new File(path);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }


        }

    }


}