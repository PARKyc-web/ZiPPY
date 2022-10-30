package com.yedam.zippy.chat.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.yedam.zippy.chat.service.ChatMessage;
import com.yedam.zippy.chat.service.ChatRoomName;
import com.yedam.zippy.chat.service.ChatRoomVO;
import com.yedam.zippy.chat.service.ChatService;

import lombok.RequiredArgsConstructor;


@CrossOrigin
@RestController
@RequestMapping("/chat")
//@RequestMapping("/zippy/chat")
@RequiredArgsConstructor
public class ChatController {
   
    private final ChatService service;
    
    private final String chatFolder = "C:/dev/chat/";
//    private final String chatFolder = "/home/chat";
    
    
    // 모든 채팅방 목록 반환
    @GetMapping("/room")
    public List<ChatRoomName> getRooms(String email){
      System.out.println(service.findAllRoom(email));
      return service.findAllRoom(email);
    }
    
    @GetMapping("/oneRoom")
    public ChatRoomName getOneRoom(int roomId) {  
      return service.findOneRoom(roomId);
    }
    
    @GetMapping("/roomInfo")
    public ChatRoomVO getRoomInfo(int roomId) {
      return service.getRoomInfo(roomId);
    }
    
    @GetMapping("/room/load")
    public List<ChatMessage> loadContent(String roomId){
        return service.loadChatContent(roomId);                   
    }
    
    // 채팅방 생성
    @PostMapping("/room")
    public int createChatRoom(@RequestBody ChatRoomVO vo) {      
      service.createChatRoom(vo);
      return vo.getChatRoomNo();
    }    
    
    @PutMapping("/sell")
    public int productSell(@RequestBody ChatRoomVO vo) {
      System.out.println(vo);
      return service.sellProduct(vo);
    }
    
    @GetMapping("txtFile/{id}")
    public void getTxtFile(HttpServletResponse response, @PathVariable int id) throws Exception {      
      try {
        String path = chatFolder + "roomNum" + id + ".txt";    
        
        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());                                                                                           

        FileInputStream fileInputStream = new FileInputStream(path);
        OutputStream out = response.getOutputStream();

        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = fileInputStream.read(buffer)) != -1) {
          out.write(buffer, 0, read);
        }

      } catch (Exception e) {
        throw new Exception("download error");
      }
    }
    
    @GetMapping("pdfFile/{id}")
    public void getPdfFile(HttpServletResponse response, @PathVariable int id) throws Exception {
      txtToPdf(id);
      try {        
        String path = chatFolder + "chatLog" + id + ".pdf";    
        
        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
                                                                                           

        FileInputStream fileInputStream = new FileInputStream(path);
        OutputStream out = response.getOutputStream();

        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = fileInputStream.read(buffer)) != -1) {
          out.write(buffer, 0, read);
        }

      } catch (Exception e) {
        throw new Exception("download error");
      }
    }    
    
    private void txtToPdf(int id) {
      BufferedReader input = null;
      Document output = null;
      
      String path = chatFolder + "roomNum" + id + ".txt";   
      String outFile = chatFolder + "chatLog" + id + ".pdf";
      File file = new File(path);
      try {
        input = new BufferedReader (new FileReader(file));
        output = new Document(PageSize.LETTER, 40, 40, 40, 40);
        PdfWriter.getInstance(output, new FileOutputStream (outFile));

        output.open();
        output.addAuthor("RealHowTo");
        output.addSubject("pdf");
        output.addTitle("pdf");
        
        BaseFont objBaseFont = BaseFont.createFont("font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);        
        Font objFont = new Font(objBaseFont, 12);
        
        String line = "";
        while(null != (line = input.readLine())) {
          Paragraph p = new Paragraph(line, objFont);
          p.setAlignment(Element.ALIGN_JUSTIFIED);          
          output.add(p);
        }
        output.close();
        input.close();        
      }
      catch (Exception e) {
        e.printStackTrace();        
      }
    }
    
    @PostMapping("/soldout")
    public int soldOut(@RequestBody ChatRoomVO vo) {
      System.out.println(vo);
      return service.soldout(vo);
    }

}
