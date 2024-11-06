package com.promocode_functionality.promocode_functionality.services;

import com.promocode_functionality.promocode_functionality.dtos.PromocodeRequest;
import com.promocode_functionality.promocode_functionality.dtos.PromocodeResponse;
import com.promocode_functionality.promocode_functionality.entities.Promocodes;
import com.promocode_functionality.promocode_functionality.exceptions.DuplicatePromoCodeException;
import com.promocode_functionality.promocode_functionality.exceptions.InvalidPromocodeException;
import com.promocode_functionality.promocode_functionality.exceptions.PromocodeNotFoundException;
import com.promocode_functionality.promocode_functionality.repositories.PromocodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocodesService {

    @Autowired
    private PromocodesRepository promocodesRepository;

    public String validatePromocode(String code) {
        Promocodes promocode = promocodesRepository.findByCode(code)
                .orElseThrow(() -> new InvalidPromocodeException("Promocode not found with code " + code));

        if (promocode.getStatus().equalsIgnoreCase("Active") &&
                LocalDateTime.now().isBefore(promocode.getExpiry_date())) {
            return "Promocode is valid. You will receive a discount of " + promocode.getDiscountPercent() + "%.";
        } else {
            return "Promocode is not valid.";
        }
    }

    public PromocodeResponse createPromocode(PromocodeRequest request) {
        validatePromocodeRequest(request);

        Promocodes promocode = new Promocodes();
        promocode.setCode(request.getCode());
        promocode.setExpiry_date(request.getExpiryDate());
        promocode.setStatus(request.getStatus());
        promocode.setDiscountPercent(request.getDiscountPercent());
        promocode.setCode_type(request.getCodeType());
        promocode.setGenerated_By(request.getGeneratedBy());
        promocode.setMaxUsageLimit(request.getMaxUsage());

        promocodesRepository.save(promocode);

        return mapToResponse(promocode);
    }

    public PromocodeResponse getPromocodeById(Long id) {
        Promocodes promocode = promocodesRepository.findById(id)
                .orElseThrow(() -> new PromocodeNotFoundException("Promocode not found with id " + id));
        return mapToResponse(promocode);
    }

    public List<PromocodeResponse> getAllPromocodes() {
        return promocodesRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PromocodeResponse updatePromocode(Long id, PromocodeRequest request) {
        Promocodes promocode = promocodesRepository.findById(id)
                .orElseThrow(() -> new PromocodeNotFoundException("Promocode not found with id " + id));

        validatePromocodeRequest(request);

        promocode.setCode(request.getCode() != null ? request.getCode() : promocode.getCode());
        promocode.setExpiry_date(request.getExpiryDate() != null ? request.getExpiryDate() : promocode.getExpiry_date());
        promocode.setStatus(request.getStatus() != null ? request.getStatus() : promocode.getStatus());
        promocode.setDiscountPercent(request.getDiscountPercent() != null ? request.getDiscountPercent() : promocode.getDiscountPercent());
        promocode.setCode_type(request.getCodeType() != null ? request.getCodeType() : promocode.getCode_type());
        promocode.setGenerated_By(request.getGeneratedBy() != null ? request.getGeneratedBy() : promocode.getGenerated_By());
        promocode.setMaxUsageLimit(request.getMaxUsage() != null ? request.getMaxUsage() : promocode.getMaxUsageLimit());

        promocodesRepository.save(promocode);

        return mapToResponse(promocode);
    }


    public void deletePromocode(Long id) {
        if (!promocodesRepository.existsById(id)) {
            throw new PromocodeNotFoundException("Promocode not found with id " + id);
        }
        promocodesRepository.deleteById(id);
    }

    private void validatePromocodeRequest(PromocodeRequest request) {
        if (LocalDateTime.now().isAfter(request.getExpiryDate())) {
            throw new InvalidPromocodeException("Expiry date must be in the future");
        }
        if (promocodesRepository.existsByCode(request.getCode())) {
            throw new DuplicatePromoCodeException("Promocode with code " + request.getCode() + " already exists.");
        }
    }

    public PromocodeResponse generatePromocode() {
        Promocodes promocode = new Promocodes();
        promocode.setCode(generateRandomCode(7));
        promocode.setCode_type("Referral");
        promocode.setGenerated_By("System");
        promocode.setExpiry_date(LocalDateTime.now().plusDays(7));  
        promocode.setStatus("Active");
        promocode.setDiscountPercent(10);
        promocode.setMaxUsageLimit(1);
        promocode.setCreated_at(LocalDateTime.now());


        promocodesRepository.save(promocode);

        return mapToResponse(promocode);
    }

    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (characters.length() * Math.random());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }


    private PromocodeResponse mapToResponse(Promocodes promocode) {
        PromocodeResponse response = new PromocodeResponse();
        response.setPromocodeId(promocode.getPromocode_id());
        response.setCode(promocode.getCode());
        response.setExpiryDate(promocode.getExpiry_date());
        response.setStatus(promocode.getStatus());
        response.setDiscountPercent(promocode.getDiscountPercent());
        response.setCodeType(promocode.getCode_type());
        response.setGeneratedBy(promocode.getGenerated_By());
        response.setMaxUsage(promocode.getMaxUsageLimit());
        response.setCreatedAt(promocode.getCreated_at());
        return response;
    }
}
