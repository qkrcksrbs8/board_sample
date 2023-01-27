
package cg.park.board_sample.comm.util;

public class PagingUtil {
	
	private int startCount;	 // 한 페이지에서 보여줄 게시글의 시작 번호
	private int endCount;	 // 한 페이지에서 보여줄 게시글의 끝 번호
	private StringBuffer pagingHtml;// 페이징 생성자

	/**
	 * currentPage : 현재페이지
	 * totalCount : 전체 게시물 수
	 * blockCount : 한 페이지의  게시물의 수
	 * blockPage : 한 화면에 보여줄 페이지 수
	 * pageUrl : 호출 페이지 url
	 * */
	public PagingUtil(int currentPage, int totalCount, int blockCount,
                      int blockPage, String pageUrl) {
		this(null,currentPage,totalCount,blockCount,blockPage,pageUrl);
	}

	public PagingUtil(String queryString, int currentPage, int totalCount, int blockCount,
                      int blockPage, String pageUrl) {

		// 전체 페이지 수
		int totalPage = (int) Math.ceil((double) totalCount / blockCount);	
		if (totalPage == 0) {
			totalPage = 1;
		}

		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (currentPage > totalPage)
			currentPage = totalPage;

		// 첫 진입 시 현재 페이지 1
		if (currentPage == 0)
			currentPage = 1;

		// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
		startCount = (currentPage - 1) * blockCount + 1;
		endCount = currentPage * blockCount;

		// 시작 페이지와 마지막 페이지 값 구하기.
		int startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		int endPage = startPage + blockPage - 1;

		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage)
			endPage = totalPage;

		queryString = replaceQueryString(queryString);

		// 이전 block 페이지
		pagingHtml = new StringBuffer();
		if (currentPage > blockPage) {
			if(queryString==null){//검색 미사용시
				pagingHtml.append("<a href="+pageUrl+"?pageNum="+ (startPage - 1) + ">");
			}else{
				pagingHtml.append("<a href="+pageUrl+"?"+queryString+"&pageNum="+ (startPage - 1) + ">");
			}
			pagingHtml.append("이전");
			pagingHtml.append("</a>");
		}
		pagingHtml.append("&nbsp;|&nbsp;");
		//페이지 번호.현재 페이지는 빨간색으로 강조하고 링크를 제거.
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;

			if (i == currentPage) {
				pagingHtml.append("&nbsp;<b> <font color='red'>");
				pagingHtml.append(i);
				pagingHtml.append("</font></b>");
			} else {
				if(queryString==null){//검색 미사용시
						pagingHtml.append("&nbsp;<a href='"+pageUrl+"?pageNum=");
				}else{
					pagingHtml.append("&nbsp;<a href='"+pageUrl+"?"+queryString+"&pageNum=");
				}
				pagingHtml.append(i);
				pagingHtml.append("'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}
			pagingHtml.append("&nbsp;");
		}
		pagingHtml.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		// 다음 block 페이지
		if (totalPage - startPage >= blockPage) {
			if(queryString==null){//검색 미사용시
				pagingHtml.append("<a href="+pageUrl+"?pageNum="+ (endPage + 1) + ">");
			}else{
				pagingHtml.append("<a href="+pageUrl+"?"+queryString+"&pageNum="+ (endPage + 1) + ">");
			}
			pagingHtml.append("다음");
			pagingHtml.append("</a>");
		}
	}
	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}
	public int getStartCount() {
		return startCount-1;
	}
	public int getEndCount() {
		return endCount;
	}

	public String replaceQueryString(String str) {
		if (null == str || "".equals(str.trim()))
			return "";

		int num = str.indexOf("pageNum=");
		if (-1 == num)
			return str;

		if (num != 0 && "&".equals(str.substring(num-1, num)))
			return str.substring(0, num-1) + str.substring(num+9);

		if (num == 0 && str.substring(num+9).startsWith("&"))
			return str.substring(num+10);

		return str.substring(0, num) + str.substring(num+9);
	}
}
