
package cg.park.board_sample.comm.util;

public class PagingUtil {
	
	private int startCount;	 // �� ���������� ������ �Խñ��� ���� ��ȣ
	private int endCount;	 // �� ���������� ������ �Խñ��� �� ��ȣ
	private StringBuffer pagingHtml;// ����¡ ������

	/**
	 * currentPage : ����������
	 * totalCount : ��ü �Խù� ��
	 * blockCount : �� ��������  �Խù��� ��
	 * blockPage : �� ȭ�鿡 ������ ������ ��
	 * pageUrl : ȣ�� ������ url
	 * */
	public PagingUtil(int currentPage, int totalCount, int blockCount,
                      int blockPage, String pageUrl) {
		this(null,currentPage,totalCount,blockCount,blockPage,pageUrl);
	}

	public PagingUtil(String queryString, int currentPage, int totalCount, int blockCount,
                      int blockPage, String pageUrl) {

		// ��ü ������ ��
		int totalPage = (int) Math.ceil((double) totalCount / blockCount);	
		if (totalPage == 0) {
			totalPage = 1;
		}

		// ���� �������� ��ü ������ ������ ũ�� ��ü ������ ���� ����
		if (currentPage > totalPage)
			currentPage = totalPage;

		// ù ���� �� ���� ������ 1
		if (currentPage == 0)
			currentPage = 1;

		// ���� �������� ó���� ������ ���� ��ȣ ��������.
		startCount = (currentPage - 1) * blockCount + 1;
		endCount = currentPage * blockCount;

		// ���� �������� ������ ������ �� ���ϱ�.
		int startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		int endPage = startPage + blockPage - 1;

		// ������ �������� ��ü ������ ������ ũ�� ��ü ������ ���� ����
		if (endPage > totalPage)
			endPage = totalPage;

		queryString = replaceQueryString(queryString);

		// ���� block ������
		pagingHtml = new StringBuffer();
		if (currentPage > blockPage) {
			if(queryString==null){//�˻� �̻���
				pagingHtml.append("<a href="+pageUrl+"?pageNum="+ (startPage - 1) + ">");
			}else{
				pagingHtml.append("<a href="+pageUrl+"?"+queryString+"&pageNum="+ (startPage - 1) + ">");
			}
			pagingHtml.append("����");
			pagingHtml.append("</a>");
		}
		pagingHtml.append("&nbsp;|&nbsp;");
		//������ ��ȣ.���� �������� ���������� �����ϰ� ��ũ�� ����.
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;

			if (i == currentPage) {
				pagingHtml.append("&nbsp;<b> <font color='red'>");
				pagingHtml.append(i);
				pagingHtml.append("</font></b>");
			} else {
				if(queryString==null){//�˻� �̻���
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
		// ���� block ������
		if (totalPage - startPage >= blockPage) {
			if(queryString==null){//�˻� �̻���
				pagingHtml.append("<a href="+pageUrl+"?pageNum="+ (endPage + 1) + ">");
			}else{
				pagingHtml.append("<a href="+pageUrl+"?"+queryString+"&pageNum="+ (endPage + 1) + ">");
			}
			pagingHtml.append("����");
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
