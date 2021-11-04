package ru.sber.springjpademo.persistence

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.sber.springjpademo.persistence.entity.Book
import ru.sber.springjpademo.persistence.entity.Isbn
import ru.sber.springjpademo.persistence.repository.BookRepository

@Service
class BookService {
    @Autowired
    private lateinit var bookRepository : BookRepository

    fun createBook(book: Book) = bookRepository.save(book)

    fun createBooks(books : List<Book>) = bookRepository.saveAll(books)

    fun findById(id: Long) = bookRepository.findById(id)
    fun findByName(name:String) = bookRepository.findByName(name)
    fun findAll() = bookRepository.findAll()

    fun updateBook(id: Long){
        var bookToUpdate = bookRepository.findById(id)
        bookToUpdate.get().isbn = Isbn(isbn = "11111111")
        bookRepository.save(bookToUpdate.get())
    }

    fun deleteBookById(id: Long) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id)
        }
        else
         println("No such entity")
    }

}