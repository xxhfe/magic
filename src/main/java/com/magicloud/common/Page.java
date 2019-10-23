package com.magicloud.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Page {

	/**
	 * 每页记录数
	 */
	private int maxResults = 20;
	/**
	 * 当前记录数
	 */
	private int firstResult = 0;
	/**
	 * 当前页数
	 */
	private int currentPageNo = 1;
	/**
	 * 总页数
	 */
	private int pageTotal;
	/**
	 * 总记录数  
	 */
	private int recordTotal;
	/**
	 *  是否有下一页
	 */
	private boolean hasNext;
	/**
	 *  是否有上一页  
	 */
	private boolean hasPrevious;
	private String url;
	/**
	 * @param recordTotal 总记录数
	 * @param currentPage 当前页数
	 * @param maxResults 每页记录数
	 * @return
	 */
	public static Page forInstance(int recordTotal,int currentPageNo,int maxResults,String url) {
		Page page = new Page();
		/*
		 * if(!StringUtil.isNULL(currentPage) && !StringUtil.isNULL(maxResults) &&
		 * StringUtil.isNumericZidai(currentPage) &&
		 * StringUtil.isNumericZidai(maxResults)) {
		 * page.setMaxResults(Integer.parseInt(maxResults)); currentPageNo =
		 * Integer.parseInt(currentPage); }
		 */
		page.setMaxResults(maxResults);
		int maxPage = recordTotal/maxResults;
    	if (recordTotal > maxPage * maxResults) {
    		maxPage = maxPage + 1;
    	}
    	if (currentPageNo > maxPage) { 
    		currentPageNo = maxPage;
    	}
    	if(recordTotal == 0) {
    		maxPage = 1;
    		currentPageNo = 1;
    	}
    	page.setUrl(url);
    	page.setFirstResult((currentPageNo-1)*page.getMaxResults());
    	page.setRecordTotal(recordTotal);
    	BigDecimal big_total =  new BigDecimal(page.getRecordTotal()).divide(new BigDecimal(page.getMaxResults()), 5, RoundingMode.CEILING);
    	Double d_total =  Math.ceil(big_total.doubleValue());
    	page.setPageTotal(d_total.intValue());
    	page.setCurrentPageNo(currentPageNo);
    	if(page.getCurrentPageNo()< page.getPageTotal()){
    		page.setHasNext(true);
    	}else{
    		page.setHasNext(false);
    	}
    	if(page.getCurrentPageNo()> 1){
    		page.setHasPrevious(true);
    	}else{
    		page.setHasPrevious(false);
    	} 
    	return page;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 每页记录数
	 */
	public int getMaxResults() {
		return maxResults;
	}
	/**
	 * 每页记录数
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	/**
	 * 当前页数
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	/**
	 * 当前页数
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getRecordTotal() {
		return recordTotal;
	}
	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrevious() {
		return hasPrevious;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
