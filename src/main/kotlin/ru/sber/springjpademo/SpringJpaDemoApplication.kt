package ru.sber.springjpademo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.findByIdOrNull
import ru.sber.springjpademo.persistence.BookService
import ru.sber.springjpademo.persistence.entity.Author
import ru.sber.springjpademo.persistence.entity.Book
import ru.sber.springjpademo.persistence.entity.BookInfo
import ru.sber.springjpademo.persistence.entity.Isbn
import ru.sber.springjpademo.persistence.repository.BookRepository

@SpringBootApplication
class SpringJpaDemoApplication(
    private val bookService: BookService
) : CommandLineRunner {
    override fun run(vararg args: String?) {

        val author = Author(name = "Secret Author")
        val book1 = Book(
            name = "Война и мир",
            isbn = Isbn(isbn = "978-5-389-14702-7"),
            author = mutableListOf(Author(name = "Лев Николаевич Толстой"), author),
            bookInfo = BookInfo("война и мир", 80)
        )
        val book2 = Book(
            name = "Маленький принц",
            isbn = Isbn(isbn = "978-5-389-14702-8"),
            author = mutableListOf(Author(name = "Антуан де Сент-Экзюпери"), author),
            bookInfo = BookInfo("лил принц", 10)
        )
        println("Create")
        bookService.createBooks(listOf(book1,book2))
        var allBooks = bookService.findAll()
        allBooks.forEach{ println(it) }

        println("Read")
        var war = bookService.findById(book1.id)
        println(war)

        println("Update")
        bookService.updateBook(book1.id)
        war = bookService.findById(book1.id)
        println(war)
        
        println("Delete")
        bookService.deleteBookById(book1.id)
        allBooks = bookService.findAll()
        allBooks.forEach{ println(it) }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringJpaDemoApplication>(*args)
}
