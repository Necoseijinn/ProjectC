package ProjectC.Gold.Note.JWeb.J2EEV1.tool;

import java.util.*;

public class PageHelper {
    /***
     * 当前页面第一个元素的角标
     */
    private int start;

    /***
     * 页面上每一页要显示的元素的数量
     */
    private int count;

    /***
     * 前一页第一个元素的角标
     */
    private int pre;

    /***
     * 下一页第一个元素的角标
     */
    private int next;

    /***
     * 最后一页第一个元素的角标
     */
    private int last;

    /***
     * 元素的总数
     */
    private int total;

    /***
     * 目前所在的页面的页面数值
     */
    private int currentPage;

    /***
     * 在页面上最多要显示的页面数量
     * 比如如果 showPageCount 的值为 3
     * 那么页面上最多会显示3个可选的页面
     * 例：
     *    [2][3][4]
     */
    private int showPageCount;

    /***
     * halfPageCount=showPageCount/2
     * 假设 showPageCount 为 3
     * halfPageCount 就为 1 (3/2=1)(int类型相除只取小数点之前的值)
     * 如果当前页面为 5
     * 那么此时页面就是如下的样式
     *    [4][5][6]
     * halfPageCount就代表 [5] 之后还可以有几个页面(总页面大于5的情况且总页面减去5大于halfPageCount的情况下)
     */
    private int halfPageCount;

    /***
     * 统计的所有页面数值对应其页面第一个元素角标的Map
     */
    private Map<Integer, Integer> pageMap;

    /***
     * 判断当前页面是否存在[首页]标签
     */
    private boolean homeTag;

    /***
     * 判断当前页面是否存在[尾页]标签
     */
    private boolean lastTag;

    /***
     * 判断当前页面是否存在[前一页]的标签
     */
    private boolean preTag;

    /***
     * 判断当前页面是否存在[下一页]的标签
     */
    private boolean nextTag;

    /***
     * 判断当前页面是否不存在任何标签
     * (没有任何元素或者元素只够显示一页的情况下)
     */
    private boolean noTag;

    /***
     * 当前页面显示所需要的页面数值对应其页面第一个元素角标的Map
     */
    private Map<Integer, Integer> showPageMap;

    /***
     *
     * 初始化方法
     * 根据传入的信息解析出目前页面所需要的全部信息。
     *
     * @param start 当前页面第一个元素的角标
     * @param count 页面上每一页要显示的元素的数量
     * @param showPageCount 在页面上最多要显示的页面数量
     * @param total 元素的总数
     * @throws Exception
     */
    public void initPageset(int start, int count, int showPageCount, int total) throws Exception {

        /** 传入的最大页面数值不可小于2 **/
        if (showPageCount < 2) {
            throw new Exception("showPageCount can not be smaller than 2.");
        }

        /***
         * 当传入的 showPageCount 为2时 halfPageCount 赋值为1
         * 其余情况一律 halfPageCount=showPageCount/2
         */
        if (showPageCount % 2 == 0 && showPageCount / 2 == 1) {
            halfPageCount = 1;
        } else {
            halfPageCount = showPageCount / 2;
        }

        /** 给对应值进行赋值 **/
        this.start = start;
        this.count = count;
        this.total = total;
        this.showPageCount = showPageCount;

        /** 计算出当前页面所需要的一些特定值 **/
        pre = start - count;
        next = start + count;
        last = total % count == 0 ? total - count : total - total % count;
        currentPage = start / count + 1;
        noTag = last < 0 || last == 0;
        homeTag = start != 0;
        lastTag = last > 0 && last != start;
        preTag = pre >= 0;
        nextTag = next <= last && next != start;

        /***
         * 获取所有页面数值对应其页面第一个元素角标Map的信息
         * 简单来说就是
         *
         *    元素1  ---->  元素角标为0
         *    元素2
         *    元素3
         *    [1][2][3]
         *
         *    假设当前页面是[1]，则Map中存储的就是 1-->0
         */
        pageMap = new LinkedHashMap<Integer, Integer>();
        for (int i = 0, k = 1; i <= total; i++) {
            /** 跳过 **/
            if (i == 0) {
                continue;
            }
            /** 如果i能被count整除说明此时角标为i的元素是在一个新页面的第一个元素 **/
            if (i % count == 0) {
                pageMap.put(k, i - count);
                /** k 代表页面数 **/
                k++;
                continue;
            }
            /** 当i等于总元素数并且i不能被count整除时 **/
            if (i == total) {
                /***
                 * 假设 i=11 count=5
                 * 那么此时k=3
                 * 我们需要把第3页第一个元素的角标值计算出来并放到Map中
                 * 也就是 11-11%5 ---> 11-1=10
                 */
                pageMap.put(k, i - i % count);
            }
        }

        /***
         * 获取当前页面上需要显示的所有页面数与其页面数对应的第一个元素的角标的Map
         */
        showPageMap = getPageIndex();
    }

    private Map<Integer, Integer> getPageIndex() {

        Map<Integer, Integer> showPages = new LinkedHashMap<Integer, Integer>();

        /***
         * 没有任何元素或者元素不满一页的时候返回null
         */
        if (noTag) {
            return null;
        }

        /***
         * 如果页面总数小于需要显示的最大页面数
         * 那么就直接把目前有的所有页面数当作显示页面数
         * 比如
         * 假设当前页面总数为3，而页面最大显示数为5。
         * 那么画面上就是这个样子
         *
         *    [1][2][3]
         */
        if (pageMap.size() <= showPageCount) {
            return pageMap;
        }

        /***
         * 当当前页面数还不足以使得整个显示页面组变化时会执行下面的步骤
         * 比如
         * 假设当前页面数为2，页面总数为10，最大显示页面数为5，halfPageCount就为2。
         * 那么此时画面就是
         *
         *    [1][2][3][4][5]
         *
         * 当前页面变为3时画面还是
         *
         *    [1][2][3][4][5]
         *
         */
        if (currentPage <= (showPageCount - halfPageCount)) {
            for (int i = 1; i <= showPageCount; i++) {
                showPages.put(i, pageMap.get(i));
            }
            return showPages;
        }

        /***
         * 如果当前页面数值已经接近末尾的情况。
         * 比如假设当前页面为第11页，总页面数为13，最大显示页面数为5，halfPageCount就为2。
         * 也就是：
         *
         *    [9][10][11][12][13]
         *
         * 假如当前页面变成了12，画面还是
         *
         *    [9][10][11][12][13]
         *
         */
        if ((pageMap.size() - currentPage) <= halfPageCount) {
            for (int i = pageMap.size() - showPageCount + 1; i <= pageMap.size(); i++) {
                showPages.put(i, pageMap.get(i));
            }
            return showPages;
        } else {
            /***
             * 其余情况都按照下面的步骤执行
             */
            for (int i = currentPage + halfPageCount - showPageCount + 1; i <= currentPage + halfPageCount; i++) {
                showPages.put(i, pageMap.get(i));
            }
            return showPages;
        }
    }

    public int getPre() {
        return pre;
    }

    public int getNext() {
        return next;
    }

    public int getLast() {
        return last;
    }

    public boolean isHomeTag() {
        return homeTag;
    }

    public boolean isLastTag() {
        return lastTag;
    }

    public boolean isPreTag() {
        return preTag;
    }

    public boolean isNextTag() {
        return nextTag;
    }

    public boolean isNoTag() {
        return noTag;
    }

    public Map<Integer, Integer> getShowPageMap() {
        return showPageMap;
    }
}
