package com.itwillbs.domain;

public class Criteria {
	// 페이징처리 하기위한 정보를 저장하는 객체
	
	// 페이지 정보(시작지점)
	private int page;
	
	// 페이지 크기
	private int pageSize;
	
	// 사용자 정의 값 설정
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page =1;
			return; // 비정상적인 데이터 입력시 1페이지로 고정하기
		}
		
		this.page = page;
	}
	public int getPage() {
		return page;
	}

	// 한번에 가져올 글의 개수를 지정
	public void setPageSize(int pageSize) {
		// 비정상적인 페이지크기를 받을때 기본값으로 지정
		if(pageSize <= 0 || pageSize > 100) {
			this.pageSize = 10;
			return;
		}
		
		this.pageSize = pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	// 0, 10, 20, 30.....
	// 시작 데이터 번호 = (페이지 번호 -1)*페이지에서 보여질 글의 갯수;
	
	// limit - 시작값 계산(* mapper에서 호출)
	public int getPageStart() {
		return (this.page-1) * pageSize;
	}
	
	// 기본값 (기본 생성자)
	public Criteria() {
		this.page = 1;
		this.pageSize = 10;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
	
}
