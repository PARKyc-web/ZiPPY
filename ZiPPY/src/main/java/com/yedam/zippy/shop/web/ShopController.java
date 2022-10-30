package com.yedam.zippy.shop.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.ReviewBoardVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.shop.service.CartVO;
import com.yedam.zippy.shop.service.OrderVO;
import com.yedam.zippy.shop.service.ProductOptionVO;
import com.yedam.zippy.shop.service.ProductVO;
import com.yedam.zippy.shop.service.PurchaseVO;
import com.yedam.zippy.shop.service.QnaVO;
import com.yedam.zippy.shop.service.ShopService;

@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {

  @Autowired
  ShopService service;

  // 상품 CRUD
  // 전체조회
  @RequestMapping("/main")
  public List<ProductVO> productList() {
    return service.getProductList();
  }

  // 전체조회(카테고리)
  @RequestMapping("/category")
  public PageInfo<ProductVO> categoryList(int pageNum, @RequestParam("cate") String category,
      @RequestParam(value = "dropbox", required = false) String dropbox) {
    String order = "pro_no DESC";
    if (dropbox.equals("최신순")) {
      dropbox = "0";
      order = "pro_no DESC";
    } else if (dropbox.equals("최저가순")) {
      dropbox = "1";
      order = "pro_price ASC";
    } else if (dropbox.equals("최고가순")) {
      dropbox = "2";
      order = "pro_price DESC";
    }
    PageHelper.startPage(pageNum, 20, order);
    return PageInfo.of(service.getCategoryList(category, dropbox));
  }

  // 전체조회(키워드)
  @RequestMapping("/keyword")
  public PageInfo<ProductVO> keywordList(int pageNum, @RequestParam("keyw") String keyword,
      @RequestParam(value = "dropbox", required = false) String dropbox) {
    String order = "pro_no DESC";
    if (dropbox.equals("최신순")) {
      dropbox = "0";
      order = "pro_no DESC";
    } else if (dropbox.equals("최저가순")) {
      dropbox = "1";
      order = "pro_price ASC";
    } else if (dropbox.equals("최고가순")) {
      dropbox = "2";
      order = "pro_price DESC";
    }
    PageHelper.startPage(pageNum, 20, order);
    return PageInfo.of(service.getKeywordList(keyword, dropbox));
  }

  // 단건조회(디테일)
  @RequestMapping("/detail")
  public ProductVO productDetail(@RequestParam("pno") int proNo) {
    return service.getProduct(proNo);
  }

  // 조회(이미지)
  @RequestMapping("/img")
  public String[] imgDetail(@RequestParam("pno") int proNo) {
    return service.getDetailImg(proNo);
  }

  // 조회(옵션)
  @RequestMapping("/opt")
  public List<ProductOptionVO> optDetail(@RequestParam("pno") int proNo) {
    return service.getDetailOpt(proNo);
  }

  // 장바구니 CRUD
  // 장바구니 등록
  @PostMapping("/insertCart")
  public void insertCart(CartVO cartVO) {
    service.insertCart(cartVO);
  }

  // 장바구니 개수 조회
  @RequestMapping("/myCart")
  public int countMyCart(String email) {
    return service.getMyCart(email);
  }

  // 내 장바구니 조회
  @PostMapping("/myCartList")
  public List<CartVO> getMyCartList(String email) {
    return service.getMyCartList(email);
  }

  // 삭제
  @PostMapping("/delCart")
  public void deleteCart(@RequestBody List<CartVO> selected) {
    service.deleteCart(selected);
  }

  // 주문 CRUD
  // 주문할 상품 등록(장바구니)
  @PostMapping("/insertPur")
  public void insertPur(@RequestBody List<CartVO> selected, String payCode) {
    service.insertPur(selected, payCode);
  }

  // 주문할 상품 등록(바로구매)
  @PostMapping("/insertPurOne")
  public void insertPurOne(@RequestBody ProductVO product, String payCode, String email) {
    service.insertPurOne(product, payCode, email);
  }

  // 내 정보 조회
  @PostMapping("/myInfo")
  public GeneralUserVO getMyInfo(String email) {
    return service.getMyInfo(email);
  }

  // 상품정보 조회
  @PostMapping("/myPurPro")
  public List<PurchaseVO> getMyPurList(String payCode, @RequestParam(required = false) Integer purNo) {
    return service.getMyPurList(payCode, purNo);
  }

  // 주문등록
  @PostMapping("/insertOrder")
  public void insertOrder(@RequestBody OrderVO orderVO) {
    service.insertOrder(orderVO);
  }

  // 주문서 리스트 조회
  @PostMapping("/myOrderList")
  public List<OrderVO> getMyOrderList(String email) {
    return service.getMyOrderList(email);
  }

  // 주문 단건조회
  @PostMapping("/oneOrder")
  public OrderVO getOneOrder(String payCode) {
    return service.getOneOrder(payCode);
  }

  // 상품상태 업데이트
  @PostMapping("/updateRvStatus")
  public int updateRvStatus(@RequestBody PurchaseVO purchaseVO) {
    return service.updateRvStatus(purchaseVO);
  }

  // QNA CRUD
  // 등록(qna)
  @PostMapping("/insertQna")
  public void insertQna(@RequestBody QnaVO qnaVO) {
    service.insertQna(qnaVO);
  }

  // 조회(qna)
  // @ResponseBody
  @GetMapping("/getQnaList")
  public PageInfo<QnaVO> getQnaList(int pageNum, int proNo) {
    String order = "";
    PageHelper.startPage(pageNum, 5, order);
    return PageInfo.of(service.getQnaList(proNo));
  }

  // 마이페이지
  // My wish
  @GetMapping("/getMyWishList")
  public PageInfo<Map<ProductVO, BookmarkVO>> getMyWishList(int pageNum, String email) {
    String order = "";
    PageHelper.startPage(pageNum, 10, order);
    return PageInfo.of(service.getMyWishList(email));
  }
  // My Qna
  @PostMapping("/getMyQna")
  public List<Map<QnaVO, ProductVO>> getMyQna(String email){
    return service.getMyQna(email);
  }
  //My review
   @PostMapping("/getMyRv")
   public List<Map<ReviewBoardVO, ProductVO>> getMyRv(String email){
     return service.getMyRv(email);
   }

  // ***
  // 판매자 CRUD
  // 상품등록
  @PostMapping("/insertPro")
  public void insertProduct(ProductVO productVO, String option, MultipartFile image, List<MultipartFile>
  images) {
    Gson gs = new Gson();
    List<ProductOptionVO> options = gs.fromJson(option, new TypeToken<ArrayList<ProductOptionVO>>() {
    }.getType());
    service.insertProduct(productVO, options, image, images);
  }

  // 상품조회
  // @ResponseBody
  @PostMapping("/myProList")
  public List<ProductVO> getMyProList(@RequestBody ProductVO productVO,
      @RequestParam(required = false) String keyword) {
    return service.getMyProList(productVO, keyword);
  }

  // 등록 상품상태 수정
  @PostMapping("/updateStatus")
  public void updateStatus(@RequestBody ProductVO productVO) {
    service.updateStatus(productVO);
  }

  // 상품정보 수정
  @PostMapping("/updatePro")
  public void updateProduct(ProductVO productVO, String option, MultipartFile image, List<MultipartFile>
  images) {
    Gson gs = new Gson();
    List<ProductOptionVO> options = gs.fromJson(option, new TypeToken<ArrayList<ProductOptionVO>>() {
    }.getType());
    service.updateProduct(productVO, options, image, images);
  }

  // 판매 상품조회
  // @ResponseBody
  @PostMapping("/sellerPurList")
  public List<PurchaseVO> getSellerPurList(@RequestBody ProductVO productVO,
      @RequestParam(required = false) String keyword) {
    return service.getSellerPurList(productVO, keyword);
  }

  // 판매 상품상태 수정
  @PostMapping("/updateOrdStatus")
  public void updateOrdStatus(@RequestBody PurchaseVO purchaseVO) {
    service.updateOrdStatus(purchaseVO);
  }

  // QNA 전체조회
  @GetMapping("/getSellerQnaList")
  public List<QnaVO> getSellerQnaList(String email) {
    return service.getSellerQnaList(email);
  }

  // QNA 수정
  @PostMapping("/updateQnaAnswer")
  public int updateQnaAnswer(@RequestBody QnaVO qnaVO) {
    return service.updateQnaAnswer(qnaVO);
  }
}