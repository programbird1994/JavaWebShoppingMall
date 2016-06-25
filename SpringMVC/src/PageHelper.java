/**
 * 
 */

 
import java.util.List;
 
/**
 * 
 * @author hh
 * 
 */
public class PageHelper
{
    // �ܹ���������
    private int total;
 
    // ÿҳ��ʾ������
    private int pageSize;
 
    // ���ж���ҳ
    private int totalPage;
 
    // ��ǰ�ǵڼ�ҳ
    private int index;
 
    // ����
    private List<?> data;
 
    // ����·��
    private String path = "";
 
    /**
     * ҳ��HTML��Ϣ
     */
    @SuppressWarnings("unused")
    private String pageHTML;
 
    private int startPage; // ��ʼҳ��
 
    private int endPage; // ����ҳ��
 
    private int displayNum = 5; // ��ʾ��ҳ��
 
    /**
     * @return the startPage
     */
    public int getStartPage()
    {
        return startPage;
    }
 
    /**
     * @return the endPage
     */
    public int getEndPage()
    {
        return endPage;
    }
 
    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }
 
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
 
    public void setIndex(int index)
    {
        this.index = index;
    }
 
    /**
     * ����·��ǰ׺��ҳ���һҳindexΪ1
     * 
     * @param path
     *            ��path���в�����ʽ����/aa/article?page=,����/bb/article/list/
     */
    public void setPath(String path)
    {
        this.path = path;
    }
 
    public int getPageSize()
    {
        return pageSize;
    }
 
    public int getTotalPage()
    {
        return (this.total + this.pageSize - 1) / this.pageSize;
    }
 
    public int getIndex()
    {
        return index;
    }
 
    public List<?> getData()
    {
        return data;
    }
 
    public void setData(List<?> data)
    {
        this.data = data;
    }
 
    /**
     * @return the total
     */
    public int getTotal()
    {
        return total;
    }
 
    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total)
    {
        this.total = total;
    }
 
    public String getPageHTML()
    {
        totalPage = getTotalPage();
        StringBuffer displayInfo = new StringBuffer();
        if (totalPage != 0 && pageSize != 0)
        {
            displayInfo.append("<div class='pager'>");
            displayInfo.append("<span class='item nolink'>��<span class='num'>" + totalPage + "</span>ҳ/<span class='num'>" + total + "</span>����¼</span>");
            //displayInfo.append("<span class='item nolink'>/<span class='num'>" + total + "</span>����¼</span>");
            // �ж������ǰ�ǵ�һҳ ����ҳ���͡���һҳ��ʧȥ����
            if (index <= 1)
            {
                displayInfo.append("<span class='item nolink'>��ҳ</span>");
                displayInfo.append("<span class='item nolink'>��һҳ</span>");
            }
            else
            {
                displayInfo.append("<span class='item'><a href='" + path + "1'>��ҳ</a></span>");
                displayInfo.append("<span class='item'><a href='" + path + (index - 1) + "'>��һҳ</a></span>");
            }
 
            countPages();
            displayInfo.append("<span class='item nums'>");
            for (int i = startPage; i <= endPage; i++)
            {
                if (i == index)
                {
                    displayInfo.append("<span class='nolink'>" + i + "</span>");
                }
                else
                {
                    displayInfo.append("<a href='" + path + i + "'>" + i + "</a>");
                }
            }
            displayInfo.append("</span>");
 
            if (index >= totalPage)
            {
                displayInfo.append("<span class='item nolink'>��һҳ</span>");
                displayInfo.append("<span class='item nolink'>βҳ</span>");
            }
            else
            {
                displayInfo.append("<span class='item'><a href='" + path + (index + 1) + "'>��һҳ</a></span>");
                displayInfo.append("<span class='item'><a href='" + path + totalPage + "'>βҳ</a></span>");
            }
            displayInfo.append("</div>");
        }
        return displayInfo.toString();
    }
 
    /**
     * ������ʼҳ�ͽ���ҳ
     */
    public void countPages()
    {
 
        if (index - displayNum / 2 < 1)
        {
            startPage = 1;
            endPage = displayNum > totalPage ? totalPage : displayNum;
        }
        else if (index + displayNum / 2 > totalPage)
        {
            int n = totalPage - displayNum + 1;
            startPage = n > 0 ? n : 1;
            endPage = totalPage;
        }
        else
        {
            startPage = index - displayNum / 2;
            endPage = startPage + displayNum - 1;
        }
    }
 
    /**
     * @param pageHTML the pageHTML to set
     */
    public void setPageHTML(String pageHTML)
    {
        this.pageHTML = pageHTML;
    }
 
    public static void main(String[] args)
    {
        PageHelper p = new PageHelper();
        // p.totalPage = p.getTotalPage();
        p.setTotal(1002);
        p.setPageSize(20);
        p.setPath("/bb/article/list/");
        // for (int i = -80; i < 80; i++)
        // {
        // p.setIndex(i);
        // p.countPages();
        // System.out.println(i+"----"+p.getStartPage() + "-----" +
        // p.getEndPage());
        // }
        p.setIndex(33);
        System.out.println(p.getPageHTML());
    }
}