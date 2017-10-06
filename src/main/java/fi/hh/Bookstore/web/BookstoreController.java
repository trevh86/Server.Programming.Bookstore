package fi.hh.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.Bookstore.domain.Book;
import fi.hh.Bookstore.domain.BookRepository;
import fi.hh.Bookstore.domain.CategoryRepository;

@Controller
public class BookstoreController {
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Show all books
	@RequestMapping(value="/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
	
	// Login page.
		@RequestMapping(value="/login")
		public String login() {
			return "login";
		}
	
	// Add new book
	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}
	
	// Save new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    }

	// Delete book
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.delete(bookId);
        return "redirect:/booklist";
    }
    
    // Edit book
    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Book book, Model model){
    	model.addAttribute("book", brepository.findOne(bookId));
		model.addAttribute("categorys", crepository.findAll());
    	return "edit";
    }
    
  	// RESTful service to get all books
      @RequestMapping(value="/books", method = RequestMethod.GET)
      public @ResponseBody List<Book> bookListRest() {	
          return (List<Book>) brepository.findAll();
      }    

  	// RESTful service to get book by id
      @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
      public @ResponseBody Book findBookRest(@PathVariable("id") Long id) {	
      	return brepository.findOne(id);
      }
    
    
}