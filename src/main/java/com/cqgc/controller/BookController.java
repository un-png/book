package com.cqgc.controller;

import com.cqgc.domain.Book;
import com.cqgc.domain.Code;
import com.cqgc.domain.Result1;
import com.cqgc.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
@Api(tags = "图书管理")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @ApiOperation("添加图书")
    public Result1 save(@RequestBody Book book) {
        boolean flag = bookService.save(book);
        //参数1：如果为真，返回添加成功，否则返回失败。参数2：数据 true或false
        return new Result1(flag ? Code.SAVE_OK:Code.SAVE_ERR, flag);
    }

    @PutMapping
    @ApiOperation("修改图书")
    public Result1 update(@RequestBody Book book) {
        boolean flag = bookService.update(book);
        return new Result1(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除图书")
    public Result1 delete(@PathVariable Integer id) {
        boolean flag = bookService.delete(id);
        return new Result1(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    @GetMapping("/{id}")
    @ApiOperation("查询图书")
    public Result1 getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        //对象不为空表示成功，否则表示失败
        Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
        //封装消息：对象不为空返回空串，否则返回查询失败
        String msg = book != null ? "" : "数据查询失败，请重试！";
        //状态码，数据，信息
        return new Result1(code,book,msg);
    }

    @GetMapping
    @ApiOperation("查询所有图书")
    public Result1 getAll() {
        List<Book> bookList = bookService.getAll();
        //集合不为空则返回成功
        Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
        //集合不为空返回空串，否则返回查询失败
        String msg = bookList != null ? "" : "数据查询失败，请重试！";
        //状态码，数据，信息
        return new Result1(code,bookList,msg);
    }

    @GetMapping("/search")
    @ApiOperation("条件查询")
    public Result1 searchBooks(@RequestParam(required = false) String type, 
                               @RequestParam(required = false) String name) {
        Book book = new Book();
        book.setType(type);
        book.setName(name);
        List<Book> bookList = bookService.getBooks(book);
        Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = bookList != null ? "" : "数据查询失败，请重试！";
        return new Result1(code, bookList, msg);
    }
}