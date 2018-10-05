package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.ResBoard;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) throws Exception{
		System.out.println(vo.getSeq());
		System.out.println(vo.getTitle());
		
		boardService.deleteBoard(vo);

		return "getBoardList.do";
	}
	
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO board, Model model) {
		//게시글 조회수 증가
	    boardService.updateBoardViewCount(board);
		//게시글 내용 보기
		board = boardService.getBoard(board);
		
		System.out.println("board.getSeq()="+board.getSeq());
		//session에 객체 저장
        model.addAttribute("board",board);
		System.out.println("content 여기까지");
	
		return "getBoard.jsp";
	}
	
	
	//RESTful 리스트
		@RequestMapping("/m/boards/json")
		@ResponseBody//body만 json으로 변경
		public void getBoardsM(
				//request.getParameter("searchCondition")
				@RequestParam(value="searchCondition",defaultValue="TITLE",required=false) String condition,
				@RequestParam(value="searchKeyword" ,defaultValue="",required=false) String keyword,
				BoardVO vo, Model model,
				HttpServletResponse response) {
			    vo.setSearchCondition(condition);
			    vo.setSearchKeyword(keyword);
			
			   System.out.println("검색조건:"+condition);
			   System.out.println("검색 단어:"+keyword);
			   
			
			   List<BoardVO> boardList = boardService.getBoardList(vo);
			  
			    //android 응답데이타
			   ResBoard resData = new ResBoard();
			   resData.setNo(1);
			   resData.setBoards(boardList);
			   //Gson파싱
			   Gson gson=new Gson();
			   //Object -> String
			   String data = gson.toJson(resData);
			   
			   //응답
			   response.setCharacterEncoding("utf-8");
			   response.setContentType("text/plain;charset=utf-8");
			   try {
				response.getWriter().append(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
		//RESTful  No 예)/boards/json/10
		@RequestMapping(value="/m/boards/json/{no}",method=RequestMethod.GET)
		@ResponseBody//body만 json으로 변경
		public void getBoardByNoM(@PathVariable int no,
				                                    HttpServletResponse response){
			   BoardVO vo = new BoardVO();
			   vo.setSeq(no);
			
			   BoardVO retVo = new BoardVO();
			    retVo = boardService.getBoard(vo);
			    
			    List<BoardVO> boardList =new ArrayList<>();
			     boardList.add(retVo);
			     
			    //android 응답데이타
				   ResBoard resData = new ResBoard();
				   resData.setNo(1);
				   resData.setBoards(boardList);
				   //Gson파싱
				   Gson gson=new Gson();
				   //Object -> String
				   String data = gson.toJson(resData);
				   
				   //응답
				   response.setCharacterEncoding("utf-8");
				   response.setContentType("text/plain;charset=utf-8");
				   try {
					response.getWriter().append(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
	//RESTful 리스트
	@RequestMapping("/boards/json")
	@ResponseBody//body만 json으로 변경
	public List<BoardVO> getBoards(
			//request.getParameter("searchCondition")
			@RequestParam(value="searchCondition",defaultValue="TITLE",required=false) String condition,
			@RequestParam(value="searchKeyword" ,defaultValue="",required=false) String keyword,
			BoardVO vo, Model model) {
		    vo.setSearchCondition(condition);
		    vo.setSearchKeyword(keyword);
		
		   System.out.println("검색조건:"+condition);
		   System.out.println("검색 단어:"+keyword);
		   
		  List<BoardVO> boardList = boardService.getBoardList(vo);
		 
		 
		 return boardList;//조회한 객체를 리턴
	}
	
	
	
	//RESTful  No 예)/boards/json/10
	@RequestMapping(value="/boards/json/{no}",method=RequestMethod.GET)
	@ResponseBody//body만 json으로 변경
	public BoardVO getBoardByNo(@PathVariable int no){
		   BoardVO vo = new BoardVO();
		   vo.setSeq(no);
		
		   BoardVO retVo = new BoardVO();
		    retVo = boardService.getBoard(vo);
		 
		 return retVo;//조회한 객체를 리턴
	}
	
	//RESTful insert
	@RequestMapping(value="/boards/json", 
			                     method=RequestMethod.POST,
								 headers={"content-type=application/json"})
	@ResponseBody
	public Map insertBoardJson(@RequestBody BoardVO vo,HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("log:/boards/json");
		//DB저장
		boardService.insertBoard(vo);
		
		Map result=new HashMap();
		result.put("result", Boolean.TRUE);
		return result;
	}

	//RESTful update
		@RequestMapping(value="/boards/json", 
				                     method=RequestMethod.PUT,
									 headers={"content-type=application/json"})
		@ResponseBody
		public Map updateBoardJson(@RequestBody BoardVO vo,HttpServletRequest request) throws IllegalStateException, IOException {
			System.out.println("log:/boards/json");
	        
			//DB수정
			if(vo!=null)
			boardService.updateBoard(vo);
			
			Map result=new HashMap();
			result.put("result", Boolean.TRUE);

			return result;
		}
 	//RESTful 삭제
		@RequestMapping(value="/boards/json/{no}",
				                      method=RequestMethod.DELETE)
		@ResponseBody//body만 json으로 변경
		public Map deleteBoardByNo(@PathVariable int no) throws Exception{
			   BoardVO vo = new BoardVO();
			   vo.setSeq(no);
			    boardService.deleteBoard(vo);
			    
			    Map result=new HashMap();
			    result.put("result", Boolean.TRUE);
			 
			 return result;//조회한 객체를 리턴
		}
		
	@RequestMapping("/dataTransform.do")
	@ResponseBody//body만 json으로 변경
	public List<BoardVO> dataTransForm(
			//request.getParameter("searchCondition")
			@RequestParam(value="searchCondition",defaultValue="TITLE",required=false) String condition,
			@RequestParam(value="searchKeyword" ,defaultValue="",required=false) String keyword,
			BoardVO vo, Model model) {
		    vo.setSearchCondition(condition);
		    vo.setSearchKeyword(keyword);
		
		   System.out.println("검색조건:"+condition);
		   System.out.println("검색 단어:"+keyword);
		   
		  List<BoardVO> boardList = boardService.getBoardList(vo);
		 
		 
		 return boardList;//조회한 객체를 리턴
	}
	
	@RequestMapping("/dataTransform2.do")
	@ResponseBody//body만 xml로 변경
	public BoardListVO dataTransform2(
			//request.getParameter("searchCondition")
			@RequestParam(value="searchCondition",defaultValue="TITLE",required=false) String condition,
			@RequestParam(value="searchKeyword" ,defaultValue="",required=false) String keyword,
			BoardVO vo, Model model) {
		    vo.setSearchCondition(condition);
		    vo.setSearchKeyword(keyword);
		
		   System.out.println("검색조건:"+condition);
		   System.out.println("검색 단어:"+keyword);
		   
		  List<BoardVO> boardList = boardService.getBoardList(vo);
		  BoardListVO boardListVO = new BoardListVO();
		  boardListVO.setBoardList(boardList);
		 
		   
		 return boardListVO;//XML파일 리턴
	}
	
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(
			//request.getParameter("searchCondition")
			@RequestParam(value="searchCondition",defaultValue="TITLE",required=false) String condition,
			@RequestParam(value="searchKeyword" ,defaultValue="",required=false) String keyword,
			BoardVO vo,  Model model) {
		    vo.setSearchCondition(condition);
		    vo.setSearchKeyword(keyword);
		
		   System.out.println("검색조건:"+condition);
		   System.out.println("검색 단어:"+keyword);
		   
		  List<BoardVO> boardList = boardService.getBoardList(vo);
		  model.addAttribute("boardList", boardList);
		 
		 return "getBoardList.jsp";//view명 설정
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo,HttpServletRequest request) throws IllegalStateException, IOException {
		String path = request.getRealPath("images"+"\\");
		
		//String path = "C:\\springWorkspace\\SpringMVCMyBatis\\src\\main\\webapp\\images\\";       
		//파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName=uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File(path+fileName));
			vo.setFilename(fileName);
		}
		
		/*System.out.println("vo.seq="+vo.getSeq());
		System.out.println("vo.title="+vo.getTitle());
		System.out.println("vo.content="+vo.getContent());*/
		
		boardService.insertBoard(vo);

		return "getBoardList.do";
	}

	@RequestMapping(value="/updateBoard.do",method=RequestMethod.POST)
	public String updateBoard(@ModelAttribute("board") BoardVO vo,HttpServletRequest request) throws IllegalStateException, IOException {
		String path = request.getRealPath("images"+"\\");
		//String path = "C:\\springWorkspace\\SpringMVCMyBatis\\src\\main\\webapp\\images\\";     
		
		//파일 업로드 처리
				MultipartFile uploadFile = vo.getUploadFile();

				System.out.println(vo.getUploadFile().getOriginalFilename());
		     if(!uploadFile.isEmpty()) {
					String fileName=uploadFile.getOriginalFilename();
					uploadFile.transferTo(new File(path+fileName));
					vo.setFilename(fileName);
				}
		  boardService.updateBoard(vo);
		
		 return "getBoardList.do";
	}

	 //검색 조건 목록 설정
	 @ModelAttribute("conditionMap")
	 public Map<String,String> searchConditionMap(){
		 Map<String,String> conditionMap = new HashMap<>();
		 conditionMap.put("제목", "TITLE");
		 conditionMap.put("내용", "CONTENT");
		 
		 return conditionMap;
	 }
}
