package br.com.mottusense.users.exception;

import br.com.mottusense.users.dto.output.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErroDTO> handleException(Exception e) {
        ErroDTO erro = new ErroDTO(HttpStatus.BAD_REQUEST.name(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    private ResponseEntity<ErroDTO> handleUsuarioNaoEncontradoException(Exception e) {
        ErroDTO erro = new ErroDTO(HttpStatus.NOT_FOUND.name(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(FilialNaoEncontradaException.class)
    private ResponseEntity<ErroDTO> handleFilialNaoEncontradaException(Exception e) {
        ErroDTO erro = new ErroDTO(HttpStatus.NOT_FOUND.name(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ConfiguracaoUsuarioNaoEncontradaException.class)
    private ResponseEntity<ErroDTO> handleConfiguracaoUsuarioNaoEncontradaException(Exception e) {
        ErroDTO erro = new ErroDTO(HttpStatus.NOT_FOUND.name(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}