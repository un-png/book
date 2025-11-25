package com.cqgc.service;

import com.cqgc.domain.Book;

import java.util.List;

 //表示所有方法进行事务管理
public interface BookService {

    /**
     * 保存
     * @param book
     * @return
     */
    public boolean save(Book book);

    /**
     * 修改
     * @param book
     * @return
     */
    public boolean update(Book book);

    /**
     * 按id删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public Book getById(Integer id);

    /**
     * 查询全部
     * @return
     */
    public List<Book> getAll();
    
    /**
     * 条件查询
     * @param book
     * @return
     */
    public List<Book> getBooks(Book book);
}