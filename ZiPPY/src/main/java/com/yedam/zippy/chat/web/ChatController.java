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
     
    // 모든 채팅방 목록 반환
    @GetMapping("/room")
    public List<ChatRoomName> getRooms(String email){
      System.out.println(service.findAllRoom(email));
      return service.findAllRoom(email);
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
    public void createChatRoom(@RequestBody ChatRoomVO vo) {
      System.out.println(vo);
      service.createChatRoom(vo);      
    }
 
    @GetMapping("txtFile/{id}")
    public void getTxtFile(HttpServletResponse response, @PathVariable int id) throws Exception {      
      try {
        String path = "C:/dev/chat/roomNum" + id + ".txt"; // 경로에 접근할 때 역슬래시('\') 사용    
        
        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를
                                                                                            // 알려주는 헤더

        FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
        OutputStream out = response.getOutputStream();

        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
          out.write(buffer, 0, read);
        }

      } catch (Exception e) {
        throw new Exception("download error");
      }
    }
    
    @GetMapping("pdfFile/{id}")
    public void getPdfFile(HttpServletResponse response, @PathVariable int id) throws Exception {
      txtTOpdf(id);
      try {        
        String path = "C:/dev/chat/chatLog" + id + ".pdf"; // 경로에 접근할 때 역슬래시('\') 사용    
        
        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를
                                                                                            // 알려주는 헤더

        FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
        OutputStream out = response.getOutputStream();

        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
          out.write(buffer, 0, read);
        }

      } catch (Exception e) {
        throw new Exception("download error");
      }
    }    
    
    private void txtTOpdf(int id) {
      BufferedReader input = null;
      Document output = null;
      System.out.println("Convert text file to pdf");
      
      String path = "C:/dev/chat/roomNum" + id + ".txt"; // 경로에 접근할 때 역슬래시('\') 사용   
      String outFile = "C:/dev/chat/chatLog" + id + ".pdf"; // 경로에 접근할 때 역슬래시('\') 사용
      File file = new File(path);
      try {
        // text file to convert to pdf as args[0]
        input = 
          new BufferedReader (new FileReader(file));
        // letter 8.5x11
        //    see com.lowagie.text.PageSize for a complete list of page-size constants.
        output = new Document(PageSize.LETTER, 40, 40, 40, 40);
        // pdf file as args[1]
        PdfWriter.getInstance(output, new FileOutputStream (outFile));

        output.open();
        output.addAuthor("RealHowTo");
        output.addSubject("pdf");
        output.addTitle("pdf");
        
        BaseFont objBaseFont = BaseFont.createFont("font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);        
        Font objFont = new Font(objBaseFont, 12);
        
        String line = "";
        while(null != (line = input.readLine())) {
          System.out.println(line);
          Paragraph p = new Paragraph(line, objFont);
          p.setAlignment(Element.ALIGN_JUSTIFIED);          
          output.add(p);
        }
        System.out.println("Done.");
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
