package br.edu.umfg.exercicioAPI.controllers;

import br.edu.umfg.entities.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequestMapping("/client") //localhost:8080/client
public class ClientController {
    private ArrayList<Client> list = new ArrayList<Client>();

    @PostMapping
    public ResponseEntity<Client> postClient(@RequestBody Client client) {
        this.list.add(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Client>> getClient() {
        return ResponseEntity.ok(this.list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable(value = "id") UUID id) {
        for (Client client : this.list) {
            if (client.getId().equals(id)) {
                return ResponseEntity.ok((client));
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        for (Client client : this.list) {
            if (client.getId().equals(id)) {
                this.list.remove(client);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/id")
    public ResponseEntity<Client> updateClient(@PathVariable UUID id, @RequestBody Client updateClient) {
        for (int i = 0; i < this.list.size(); i++) {
            Client existingClient = this.list.get(i);
            if (existingClient.getId().equals(id)) {
                existingClient.setName(updateClient.getName());
                existingClient.setLastName(updateClient.getLastName());
                existingClient.setDocument(updateClient.getDocument());
                return ResponseEntity.ok(existingClient);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
