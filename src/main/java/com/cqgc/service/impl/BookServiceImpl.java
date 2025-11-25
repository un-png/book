package com.cqgc.service.impl;

import com.cqgc.dao.BookDao;
import com.cqgc.domain.Book;
import com.cqgc.domain.Code;
import com.cqgc.exception.BusinessException;
import com.cqgc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public boolean save(Book book) {
        bookDao.save(book);
        return true;
    }

    public boolean update(Book book) {
        bookDao.update(book);
        return true;
    }

    public boolean delete(Integer id) {
        bookDao.delete(id);
        return true;
    }

    public Book getById(Integer id) {
        if (id < 0) {
            throw new BusinessException(Code.BUSINESS_ERR, "请规范操作");
        }
        return bookDao.getById(id);
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }
    
    public List<Book> getBooks(Book book) {
        return bookDao.getBooks(book);
    }
}