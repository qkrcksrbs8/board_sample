
package cg.park.board_sample.comm.util;

public class PagingUtil {
	
	private int startCount;
	private int endCount;
	private StringBuffer pagingHtml;

	public PagingUtil(int currentPage, int totalCount, int blockCount, int blockPage, String pageUrl) {
		this(null,currentPage,totalCount,blockCount,blockPage,pageUrl);
	}

	public PagingUtil(String queryString, int currentPage, int totalCount, int blockCount,
                      int blockPage, String pageUrl) {

		int totalPage = (int) Math.ceil((double) totalCount / blockCount);

		if (totalPage == 0)
			totalPage = 1;

		if (currentPage > totalPage)
			currentPage = totalPage;

		if (currentPage == 0)
			currentPage = 1;

		startCount = (currentPage - 1) * blockCount + 1;
		endCount = currentPage * blockCount;

		int startPage = ((currentPage - 1) / blockPage) * blockPage + 1;
		int endPage = startPage + blockPage - 1;

		if (endPage > totalPage)
			endPage = totalPage;

		queryString = replaceQueryString(queryString);

		pagingHtml = new StringBuffer();
		if (currentPage > blockPage)
			pagingLink(queryString, pageUrl, startPage, true);

		pagingHtml.append("&nbsp;|&nbsp;");

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;

			pagingLink(queryString, pageUrl, currentPage, i);
		}
		pagingHtml.append("&nbsp;&nbsp;|&nbsp;&nbsp;");

		if (totalPage - startPage >= blockPage)
			pagingLink(queryString, pageUrl, endPage, false);

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

	private void pagingLink(String queryString, String pageUrl, int page, boolean status) {
		page = status ? page - 1 : page + 1;
		pagingHtml
				.append(pagingLink(queryString, pageUrl, page))
				.append("back")
				.append("</a>");
	}

	private String pagingLink(String queryString, String pageUrl, int page) {
		return (queryString == null)
				? "<a href="+pageUrl+"?pageNum="+ page + ">"
				: "<a href="+pageUrl+"?"+queryString+"&pageNum="+ page + ">";
	}

	private void pagingLink(String queryString, String pageUrl, int currentPage, int i) {
		if (i == currentPage) {
			pagingHtml
					.append("&nbsp;<b> <font color='red'>")
					.append(i)
					.append("</font></b>")
					.append("&nbsp;");
			return;
		}
		pagingHtml
				.append(pagingLink(queryString, pageUrl))
				.append(i)
				.append("'>")
				.append(i)
				.append("</a>")
				.append("&nbsp;");
	}

	private String pagingLink(String queryString, String pageUrl) {
		return (queryString==null)
				? "&nbsp;<a href='"+pageUrl+"?pageNum="
				: "&nbsp;<a href='"+pageUrl+"?"+queryString+"&pageNum=";
	}

}
