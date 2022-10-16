package com.yedam.zippy.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.chat.mapper.ChatMapper;
import com.yedam.zippy.chat.service.ChatVO;

import lombok.RequiredArgsConstructor;


@CrossOrigin
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
   
    private final ChatService service;
    
    @Autowired
    private ChatMapper mapper;
    
//    // 채팅 리스트 화면
//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }
//       이부분은 Vue 에서 실행할거임
    
    
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return service.findAllRoom();
    }
    
    @GetMapping("/customRoom")
    public List<ChatVO> getCustom(String email){
      return mapper.findAllRoom(email);
    }
    
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return service.createRoom(name);
    }
    
//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
    // 이부분도 Vue에서 이동하고 정보를 Router로 다 넘겨줄거임
    
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return service.findById(roomId);
    }

}
