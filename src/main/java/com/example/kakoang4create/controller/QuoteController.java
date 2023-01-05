package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.Quote;
import com.example.kakoang4create.model.QuoteDto;
import com.example.kakoang4create.repository.QuoteRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@RequestMapping("quotes")
@RestController
public class QuoteController {

    @Autowired
    private ServletContext context;
    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes() {

        return ResponseEntity.ok(quoteRepository.findAll());
    }


    @GetMapping("/dto")
    public ResponseEntity<List<QuoteDto>> getQuotes() throws IOException {

        List<QuoteDto> quoteDtoList = new ArrayList<QuoteDto>();

        List<Quote>  allQuotes = quoteRepository.findAll();


        List<String> images = new ArrayList<String>();
        String filesPath = context.getRealPath("/images");
        File fileFolder = new File(filesPath);

        String encodeBase64 = null;

        /* if (fileFolder!=null) {

           for ( String path : allQuotes.stream().map(response-> response.getQuote()).toList() ){

                File file = new File(path);
                String extension = FilenameUtils.getExtension(file.getName());

                FileInputStream fileInputStream = new FileInputStream(file);

                byte[] bytes = new byte[(int) file.length()];
                fileInputStream.read(bytes);

                encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                images.add("data:image/" + path +";base64," +encodeBase64);

                fileInputStream.close();

            }*/

        if ( fileFolder != null) {



            for ( int i = 0 ; i < allQuotes.size(); i++) {

                String path = allQuotes.get(i).getImage();
                File file = new File(path);
                String extension = FilenameUtils.getExtension(file.getName());

                FileInputStream fileInputStream = new FileInputStream(file);

                byte[] bytes = new byte[(int) file.length()];
                fileInputStream.read(bytes);

                encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                images.add("data:image/" + extension +";base64," +encodeBase64);


                quoteDtoList.add(new QuoteDto(allQuotes.get(i).getQuote(),"data:image/" + extension +";base64," +encodeBase64,
                        allQuotes.get(i).getAuthor()));

                fileInputStream.close();

            }



        }

        return ResponseEntity.ok(quoteDtoList);


    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void addQuote(@RequestParam("image") MultipartFile image, @RequestParam("quote") String quote
                         ,  @RequestParam("author") String author
    ) throws IOException {

        File file =  new File("C:/Users/dell/spring-workspace/kako-ang-4-create-spring/src/main/resources/images/"
        + image.getOriginalFilename());

        Quote quoteToSave = new Quote();


        quoteToSave.setImage("C:/Users/dell/spring-workspace/kako-ang-4-create-spring/src/main/resources/images/"
                + image.getOriginalFilename());
        quoteToSave.setQuote(quote);
        quoteToSave.setAuthor(author);


        quoteRepository.save(quoteToSave);


            image.transferTo(file);



    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable String id) {

        return ResponseEntity.ok(quoteRepository.findById(id).orElseThrow());

    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable String id) {

        quoteRepository.deleteById(id);
    }

}

