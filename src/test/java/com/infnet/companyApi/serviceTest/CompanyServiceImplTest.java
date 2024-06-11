package com.infnet.companyApi.serviceTest;

import com.infnet.companyApi.domain.Company;
import com.infnet.companyApi.dto.CompanyDto;
import com.infnet.companyApi.repository.CompanyRepository;
import com.infnet.companyApi.service.CompanyServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void testCreateCompany() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Test Company");
        companyDto.setEmail("test@example.com");

        when(companyRepository.save(any())).thenReturn(new Company());

        CompanyDto createdCompany = companyService.createCompany(companyDto);

        assertNotNull(createdCompany);
        assertEquals(companyDto.getName(), createdCompany.getName());
        assertEquals(companyDto.getEmail(), createdCompany.getEmail());
    }

    @Test
    void testUpdateCompany() {
        UUID companyId = UUID.randomUUID();
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(companyId);
        companyDto.setName("Updated Company");
        companyDto.setEmail("updated@example.com");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new Company()));
        when(companyRepository.save(any())).thenReturn(new Company());

        CompanyDto updatedCompany = companyService.updateCompany(companyId, companyDto);

        assertNotNull(updatedCompany);
        assertEquals(companyId, updatedCompany.getId());
        assertEquals(companyDto.getName(), updatedCompany.getName());
        assertEquals(companyDto.getEmail(), updatedCompany.getEmail());
    }

    @Test
    void testDeleteCompany() {
        UUID companyId = UUID.randomUUID();

        assertDoesNotThrow(() -> companyService.deleteCompany(companyId));
        verify(companyRepository, times(1)).deleteById(companyId);
    }
}
