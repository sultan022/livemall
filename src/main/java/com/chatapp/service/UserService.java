package com.chatapp.service;

import com.chatapp.domain.*;
import com.chatapp.dto.*;
import com.chatapp.repository.*;
import com.chatapp.response.UserReviewResponse;
import com.chatapp.response.UserSearchResponse;
import com.chatapp.util.CustomException;
import com.chatapp.util.UtilBase64Image;
import com.chatapp.util.Utilities;
import org.apache.logging.log4j.util.PropertySource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserFollowerRepository userFollowerRepository;

    @Autowired
    UserPaginationRepository userPaginationRepository;

    @Autowired
    private UserReviewRatingRepository userReviewRatingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Utilities utilities;

    @Autowired
    private FollowerRepository followerRepository;


    public void signup(@Valid UserData userData) throws CustomException {

        UtilBase64Image.createDirectory(userData.getEmail());
        userData.setProfilePic(" ");
        userData.setRatingCount(0D);
        userData.setRatingSum(0D);
        userData.setAvgRating(0D);
        userRepository.save(userData);

    }

    public UserData login(@Valid UserLogin userLogin) throws CustomException, Exception {

        UserData userDataFromDB = userRepository.findUserByEmailAndPassword(userLogin.getUsername(),
                userLogin.getPassword());
        if (userDataFromDB != null) {
            userDataFromDB.setPassword("");
            userDataFromDB.setProfilePic(UtilBase64Image.getImageFromDirectory(userDataFromDB.getProfilePic()));
        } else
            throw new CustomException("Email or Password Does Not Match");

        return userDataFromDB;

    }

    public void editAccountInfo(@Valid UserData userData, String email) throws CustomException {

        Integer id = userRepository.findUserIdbyEmail(email);
        if (id == null)
            throw new CustomException("User Does Not Exists!");

        String userPictureOriginalString = "";
        if (userData.getProfilePic() != null && userData.getProfilePic().length() > 1) {
            userPictureOriginalString = userData.getProfilePic();
            String picPath = UtilBase64Image.saveBase64StringAsImage(userData.getProfilePic(), email);
            userData.setProfilePic(picPath);
        }
        userData.setId(id);
        userData.setEmail(email);
        userRepository.save(userData);

        userData.setProfilePic(userPictureOriginalString);

    }

    public UserData getAccountInfo(String email) throws CustomException {

        UserData userDataFromDB = userRepository.findUserbyEmail(email);
        userDataFromDB.setProfilePic(UtilBase64Image.getImageFromDirectory(userDataFromDB.getProfilePic()));
        return userDataFromDB;

    }

    @Transactional(readOnly = true)
    public UserDtoWithProducts getUserAndProductsDetails(@Valid String email, @Valid Integer page) throws CustomException, Exception {

        UserData userData = userRepository.findByEmailOptional(email)
                .orElseThrow(() -> new CustomException("User Not Found"));

        userData.setProfilePic(UtilBase64Image.getImageFromDirectory(userData.getProfilePic()));
        page = page * 5 - 5;
        if (page < 0)
            throw new CustomException("Incorrect Page Number");
        userData.setProductCollection(productRepository.findProductByUserIdPagination(userData.getId(), page, 5));
        userData.getProductCollection().forEach(product -> {
            product.setProductMainImage(UtilBase64Image.getImageFromDirectory(product.getProductMainImage()));

        });


        userData.setFollowerCount(userRepository.getFollowerCount(userData.getId()));
        userData.setFollowingCount(userRepository.getFollowingCount(userData.getId()));
        userData.setProductCount(productRepository.countByUserId(userData.getId()));
        UserDtoWithProducts userDtoWithProducts = new UserDtoWithProducts();

        mapUserEntityToUserDto(userDtoWithProducts, userData);

        return userDtoWithProducts;

    }

//    /*
//     *
//     * only for testing purpose
//     * user and products with Pageble pagination
//     *
//     *
//     * */
//    public UserDtoWithProducts getUserAndProductDetilasWithPagination(String userEmail, Integer pageNumber, Integer size) {
//
//        //Page<UserData> userData = userPaginationRepository.findByEmailPagination(new PageRequest(pageNumber, size));
//
//        Pageable pageable = PageRequest.of(pageNumber, size);
//        Page<UserData> page = userPaginationRepository.findAll(pageable);
//        //userPaginationRepository.
//        page.getContent().forEach(page1 -> {
//            System.out.println(page1.toString());
//        });
//
//        return null;
//    }


    private void mapUserEntityToUserDto(UserDtoWithProducts userDtoWithProducts, UserData userData) {

        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        List<ImageDTO> imageDTOs = new ArrayList<>();
        ImageDTO imageDTO = new ImageDTO();

        userDtoWithProducts.setCategory(userData.getCategory());
        userDtoWithProducts.setCity(userData.getCity());
        userDtoWithProducts.setCountry(userData.getCountry());
        userDtoWithProducts.setEmail(userData.getEmail());
        userDtoWithProducts.setFollowerCount(userData.getFollowerCount());
        userDtoWithProducts.setFollowingCount(userData.getFollowingCount());
        userDtoWithProducts.setFullName(userData.getFullName());
        userDtoWithProducts.setNickname(userData.getNickname());
        userDtoWithProducts.setPhoneNumber(userData.getPhoneNumber());
        userDtoWithProducts.setProfilePic(userData.getProfilePic());
        userDtoWithProducts.setStoreName(userData.getStoreName());
        userDtoWithProducts.setUserType(userData.getUserType());
        userDtoWithProducts.setFollowerCount(userData.getFollowerCount());
        userDtoWithProducts.setFollowingCount(userData.getFollowingCount());
        // convert products

        for (Product product : userData.getProductCollection()) {

            productDTO.setDescription(product.getDescription());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setProductMainImage(product.getProductMainImage());
            productDTO.setQuantity(product.getQuantity());

            productDTOs.add(productDTO);
            productDTO = new ProductDTO();

        }

        userDtoWithProducts.setProductCollection(productDTOs);

    }

    public void addFollower(AddFollower addFollower) throws CustomException {

        Integer followerId, followingId;

        followerId = userRepository.findUserIdbyEmail(addFollower.getSourceUserEmail());
        if (followerId == null)
            throw new CustomException("user does not exist " + addFollower.getSourceUserEmail());

        followingId = userRepository.findUserIdbyEmail(addFollower.getTargetUserEmail());

        if (followingId == null)
            throw new CustomException("user does not exist " + addFollower.getTargetUserEmail());
        // update user1 following

        UserFollower userFollower = new UserFollower();
        userFollower.setUserId(followingId);
        userFollower.setFollowerId(followerId);

        userFollowerRepository.save(userFollower);

    }


    public UserData addReviewRating(RateUserDTO rateUserDto) {


        if (rateUserDto.getRating() > 5)
            throw new CustomException("Incorrect Rating");

        Optional<UserData> userDataFromDB = userRepository.findByEmailOptional(rateUserDto.getSourceUserEmail());
        Integer sourceUserId = null;
        UserData targetUser;

        if (userDataFromDB.isPresent())
            sourceUserId = userDataFromDB.get().getId();
        if (sourceUserId != null) {
            targetUser = userRepository.findUserbyEmail(rateUserDto.getTargetUserEmail());

        } else throw new CustomException("User " + rateUserDto.getSourceUserEmail() + " does not exist");
        if (targetUser != null) {

            UserReviewRating userReviewRating = new UserReviewRating();
            mapRateUserDTOToEntity(rateUserDto, userReviewRating, sourceUserId, targetUser.getId(), userDataFromDB.get());
            userReviewRatingRepository.save(userReviewRating);
            targetUser.setRatingSum(targetUser.getRatingSum() + rateUserDto.getRating());
            targetUser.setRatingCount(targetUser.getRatingCount() + 1);
            targetUser.setAvgRating(utilities.setPrecision(Double.valueOf(targetUser.getRatingSum() / targetUser.getRatingCount())));
            userRepository.save(targetUser);
            targetUser.setProfilePic(UtilBase64Image.getImageFromDirectory(targetUser.getProfilePic()));
            return targetUser;

        } else throw new CustomException("User " + rateUserDto.getTargetUserEmail() + " does not exist");

    }

    private void mapRateUserDTOToEntity(RateUserDTO rateUserDto, UserReviewRating userReviewRating, Integer sourceUserId, Integer targetUserId, UserData userData) {

        userReviewRating.setRaterId(sourceUserId);
        userReviewRating.setUserId(targetUserId);
        userReviewRating.setRatingValue(rateUserDto.getRating());
        userReviewRating.setReview(rateUserDto.getReview());
        userReviewRating.setRaterName(userData.getFullName());
    }

    public UserReviewDTO getReviews(String userEmail, Integer page) {

        page = page * 5 - 5;
        if (page < 0)
            throw new CustomException("Incorrect Page Number");

        Optional<UserData> userData = userRepository.findByEmailOptional(userEmail);
        if (userData.isPresent()) {
            List<UserReviewResponse> userReviewResponses = new ArrayList<>();
            Optional<List<UserReviewRating>> userReviews = userReviewRatingRepository.findReviewsByUserId(userData.get().getId(), page, 5);
            userReviews.ifPresent(reviews -> {
                reviews.forEach(userReview -> userReviewResponses.add(modelMapper.map(userReview, UserReviewResponse.class)));
            });

            return new UserReviewDTO(userReviewResponses);
        } else throw new CustomException("User Does not Exists");
    }


    public UserSearchResultDTO searchUsers(SearchBy searchBy, String toSearch, Integer page) {

        page = page * 7 - 7;
        if (page < 0)
            throw new CustomException("Incorrect Page Number");

        List<UserData> list = null;
        List<UserSearchResponse> userSearchResponses = new ArrayList<>();

        switch (searchBy) {
            case COUNTRY:
                list = userRepository.findAllByCountry(toSearch, page, 7);
                break;
            case CITY:
                list = userRepository.findAllByCity(toSearch, page, 7);
                break;
            case CATEGORY:
                list = userRepository.findAllByCategory(toSearch, page, 7);
                break;


        }


        Optional.ofNullable(list).ifPresent(listFromDB -> listFromDB.forEach(userFromDB -> {
            userFromDB.setProfilePic(UtilBase64Image.getImageFromDirectory(userFromDB.getProfilePic()));
            userSearchResponses.add(modelMapper.map(userFromDB, UserSearchResponse.class));
        }));

        return new UserSearchResultDTO(userSearchResponses);
    }

    public UserFollowersDTO getUserFollowers(String userEmail, Integer page) {


        page = page * 5 - 5;
        if (page < 0)
            throw new CustomException("Incorrect Page Number");

        Integer id = userRepository.findUserIdbyEmail(userEmail);
        if (id == null)
            throw new CustomException("User does not exists");

        List<UserFollowerDTO> userFollowerDTOList = new ArrayList<>();

        Optional<List<Integer>> followerIds = userFollowerRepository.findFollowersByUserId(id, page, 5);


        if (!followerIds.isPresent())
            throw new CustomException("no Followers available");
        followerRepository.findFollowerDetailsByIds(followerIds.get()).forEach(follower -> {

            follower.setProfilePic(UtilBase64Image.getImageFromDirectory(follower.getProfilePic()));
            userFollowerDTOList.add(modelMapper.map(follower, UserFollowerDTO.class));
        });

        return new UserFollowersDTO(userFollowerDTOList.stream()
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toList()));

    }


    public UserFollowingDTO getUserFollowings(String userEmail, Integer page) {
        {


            page = page * 5 - 5;
            if (page < 0)
                throw new CustomException("Incorrect Page Number");

            Integer id = userRepository.findUserIdbyEmail(userEmail);
            if (id == null)
                throw new CustomException("User does not exists");

            List<UserFollowing> followingsSet = new ArrayList<>();

            Optional<List<Integer>> followingIds = userFollowerRepository.findFollowingsByUserId(id, page, 5);
            if (!followingIds.isPresent())
                throw new CustomException("no followings available");
            followerRepository.findFollowerDetailsByIds(followingIds.get()).forEach(following -> {

                following.setProfilePic(UtilBase64Image.getImageFromDirectory(following.getProfilePic()));
                followingsSet.add(modelMapper.map(following, UserFollowing.class));
            });


            return new UserFollowingDTO(followingsSet.stream()
                    .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                    .collect(Collectors.toList()));

        }
    }
}
