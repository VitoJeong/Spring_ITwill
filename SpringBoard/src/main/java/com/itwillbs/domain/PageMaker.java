package com.itwillbs.domain;

public class PageMaker {
	// 페이지 처리 정보를 모두 담고있는 객체
	
	// 외부(URL)에서 전달되는 정보(page, pageSize)
	private Criteria cri;
	
	// DB에서 가져온 총 글의 수 
	private int totalCount;
	
	// 계산을 통해서 사용할 변수
	private int startPage;
	private int endPage;
	private int displayPageNum = 2; // 1...10, 11...20, 21...30
	
	private boolean prev;
	private boolean next;
	
	// setter, getter
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		// 페이징 처리에 필요한 정보를 계산
		calcData();
	}
	
	public void calcData() {
		
		endPage 
		= (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		
		startPage
		= (endPage - displayPageNum) + 1;
		
		int tmpEndPage
		= (int)(Math.ceil(totalCount / (double)cri.getPageSize()));
		
		if(endPage > tmpEndPage) {
			endPage = tmpEndPage;
		}
		
		prev = startPage == 1? false:true;
		
		next = endPage * cri.getPageSize() >= totalCount? false:true;
		
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", displayPageNum=" + displayPageNum + ", prev=" + prev + ", next=" + next + "]";
	}
	
}
