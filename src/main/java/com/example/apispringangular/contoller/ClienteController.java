package com.example.apispringangular.contoller;

import com.example.apispringangular.dtos.ClienteRecordDTO;
import com.example.apispringangular.model.Cliente;
import com.example.apispringangular.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> saveClient(@RequestBody @Valid ClienteRecordDTO productRecordDto) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(productRecordDto, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(cliente));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClients() {
        List<Cliente> productsList = clientRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/clientes/{codigo}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="codigo") Long codigo){
        Optional<Cliente> cliente = clientRepository.findById(codigo);
        return cliente.<ResponseEntity<Object>>map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found."));
    }

    @PutMapping("/cliente/{codigo}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "codigo") Long codigo, @RequestBody @Valid ClienteRecordDTO clienteRecordDTO) {
        Optional<Cliente> product = clientRepository.findById(codigo);
        if (product.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var cliente = product.get();
        BeanUtils.copyProperties(clienteRecordDTO, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(cliente));
    }
    @DeleteMapping("/cliente/{codigo}")
    public void removerCliente(@PathVariable long codigo) {
        clientRepository.deleteById(codigo);

    }
}
