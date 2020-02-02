package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n\n Fetch authors read-only entities:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsReadOnlyEntities();
            
            System.out.println("\n\n Fetch authors as array of objects");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObject();
            
            System.out.println("\n\n Fetch authors as array of objects by specifying columns");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObjectColumns();
            
            System.out.println("\n\n Fetch authors as array of objects via native query");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObjectNative();
            
            System.out.println("\n\n Fetch authors as array of objects via query builder mechanism");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObjectQueryBuilderMechanism();
            
            System.out.println("\n\n Fetch authors as Spring projection (DTO):");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsDtoClass();

            System.out.println("\n\n Fetch authors as Spring projection (DTO) by specifying columns:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsDtoClassColumns();

            System.out.println("\n\n Fetch authors as Spring projection (DTO) and native query:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsDtoClassNative();

            System.out.println("\n\n Fetch authors as Spring projection (DTO) via query builder mechanism:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorByGenreAsDtoClassQueryBuilderMechanism();
        };
    }
}
/*
 * Fetching All Entity Attributes As Spring Projection (DTO)

Description: This application is a sample of fetching all attributes of an entity (Author) as a Spring projection (DTO). Commonly, a DTO contains a subset of attributes, but, sometimes we need to fetch the whole entity as a DTO. In such cases, we have to pay attention to the chosen approach. Choosing wisely can spare us from performance penalties.

Key points:

fetching the result set as a List<Object[]> or List<AuthorDto> via a JPQL of type SELECT a FROM Author a WILL fetch the result set as entities in Persistent Context as well - avoid this approach
fetching the result set as a List<Object[]> or List<AuthorDto> via a JPQL of type SELECT a.id AS id, a.name AS name, ... FROM Author a will NOT fetch the result set in Persistent Context - this is efficient
fetching the result set as a List<Object[]> or List<AuthorDto> via a native SQL of type SELECT id, name, age, ... FROM author will NOT fetch the result set in Persistent Context - but, this approach is pretty slow
fetching the result set as a List<Object[]> via Spring Data query builder mechanism WILL fetch the result set in Persistent Context - avoid this approach
fetching the result set as a List<AuthorDto> via Spring Data query builder mechanism will NOT fetch the result set in Persistent Context
fetching the result set as read-only entitites (e.g., via the built-in findAll() method) should be considered after JPQL with explicit list of columns to be fetched and query builder mechanism
 * 
 */
