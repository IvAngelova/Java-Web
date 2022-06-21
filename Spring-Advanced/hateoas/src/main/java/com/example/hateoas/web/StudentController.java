package com.example.hateoas.web;

import com.example.hateoas.model.dto.OrderDto;
import com.example.hateoas.model.dto.StudentDto;
import com.example.hateoas.model.entity.OrderEntity;
import com.example.hateoas.model.entity.StudentEntity;
import com.example.hateoas.model.mapping.StudentMapper;
import com.example.hateoas.repository.StudentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentController(StudentRepository studentRepository, StudentMapper studentMapper) {
        // WARNING: Normally we never inject repos in the controllers,
        // we do this just for test, the focus is hateoas.
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDto>>> getStudents() {
        List<EntityModel<StudentDto>> allStudents = studentRepository.
                findAll().
                stream().
                map(studentMapper::mapEntityToDTO).
                map(dto -> EntityModel.of(dto, createStudentLinks(dto))).
                collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(allStudents));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDto>>> getOrders(@PathVariable("id") Long id) {
        StudentEntity student = studentRepository.
                findById(id).orElseThrow();

        List<EntityModel<OrderDto>> orders = student.
                getOrders().
                stream().
                map(this::map).
                map(EntityModel::of).
                collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(orders));
    }

    private OrderDto map(OrderEntity order) {
        return new OrderDto().setId(order.getId()).setStudentId(order.getStudent().getId()).
                setCourseId(order.getCourse().getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDto>> getStudentsByID(@PathVariable("id") Long id) {
        StudentDto student = studentRepository.
                findById(id).
                map(studentMapper::mapEntityToDTO).
                orElseThrow();

        return ResponseEntity.ok(
                EntityModel.of(student, createStudentLinks(student))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDto>> update(
            @PathVariable Long id,
            StudentDto studentDTO) {

        //IMPLEMENTATION NOT IMPORTANT for the demo

        return ResponseEntity.ok().build();
    }

    private Link[] createStudentLinks(StudentDto studentDTO) {
        List<Link> result = new ArrayList<>();

        Link selfLink = linkTo(methodOn(StudentController.class).
                getStudentsByID(studentDTO.getId())).withSelfRel();
        result.add(selfLink);

        Link updateLink = linkTo(methodOn(StudentController.class).
                update(studentDTO.getId(), studentDTO)).withRel("update");
        result.add(updateLink);

        Link orderLink = linkTo(methodOn(StudentController.class).
                getOrders(studentDTO.getId())).withRel("orders");
        result.add(orderLink);

        return result.toArray(new Link[0]);
    }

}
