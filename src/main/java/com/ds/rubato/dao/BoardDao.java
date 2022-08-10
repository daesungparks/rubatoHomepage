package com.ds.rubato.dao;

import java.util.ArrayList;

import com.ds.rubato.dto.FBoardDto;
import com.ds.rubato.dto.FileDto;
import com.ds.rubato.dto.ReplyDto;

public interface BoardDao {

	public void fbwriteDao(String fbid ,String fbtitle , String fbcontent);//자유게시판 글쓰기
	public ArrayList<FBoardDto> fblistDao();//게시판 글목록 가져오기
	
	public FBoardDto fbviewDao(String fbnum);//자유게시판 글 내용 보기
	
	public void fbhitDao(String fbnum); //자유게시판 조회수 증가 함수
	
	public ArrayList<FBoardDto> fbTitleSearchlist(String keyword);
	//자유게시판 제목으로 검색한 결과 리스트 가져오기
	
	public ArrayList<FBoardDto> fbContentSearchlist(String keyword);
	//자유게시판 내용으로 검색한 결과 리스트 가져오기
	
	public ArrayList<FBoardDto> fbNameSearchlist(String keyword);
	//자유게시판 글쓴이으로 검색한 결과 리스트 가져오기
	
	public void fbdeleteDao(String fbnum);
	//자유게시판 글 삭제
	
	//첨부파일용 dao
	public void fbfileInsertDao(int boardnum, String filename, String orifilename,String fileurl, String fileextension);//파일 업로드
	//파일 업로드(파일이 첨부된 게시글 번호, 변경된 파일이름, 원본 파일이름, 파일 저장 경로, 파일의 확장자)
	public FileDto fbGetfileinfoDao(String boardnum);//파일이 첨부된 게시글의 번호로 검색하여 첨부된 파일의 정보 불러오기
	
	//댓글 용 dao
	public void rbwriteDao(int boardnum , String rbid , String rbcontent);
	public ArrayList<ReplyDto> rblistDao(int fbnum);//댓글이 달린 원글의 게시판 번호로 검색하여 모든 댓글리스트를 반환
	
	
	
}
