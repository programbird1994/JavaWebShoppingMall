package Utils;

public class Page {

	private long totalCount = 0;// �ܼ�¼��
	private int pageNumber = 1;//  ��ǰҳ�ţ�Ĭ����ʾ��һҳ
	private int pageSize = 1; // ÿҳ��С��Ĭ��ÿҳ20��
	private int totalPage = 0;// ��ҳ����Ĭ��Ϊ0
	private int startRow = 0;// ��ʼ��¼�кţ�Ĭ��Ϊ�ӱ�ͷ��ʼ

	/**
	* ��ҳ���㷽������setTotalCount����
	*/
	public void pagination() {
// ������ҳ��
		if (this.totalCount % pageSize == 0)
			this.totalPage = new Long(this.totalCount / pageSize).intValue();
		else
			this.totalPage = new Long(this.totalCount / pageSize).intValue() + 1;
// �ų�����ҳ��
		if (this.pageNumber < 1)
			this.pageNumber = 1;
		if (this.pageNumber > this.totalPage)
			this.pageNumber = this.totalPage;
// ������ʼ�к�
		this.startRow = (this.pageNumber - 1) * this.pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		this.pagination();
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

}
