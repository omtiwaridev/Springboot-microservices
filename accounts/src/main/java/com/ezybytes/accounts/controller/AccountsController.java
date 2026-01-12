package com.ezybytes.accounts.controller;

import com.ezybytes.accounts.dto.CustomerDto;
import com.ezybytes.accounts.dto.ErrorResponseDto;
import com.ezybytes.accounts.dto.ResponseDto;
import com.ezybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ezybytes.accounts.constants.AccountsConstants.*;

@Tag(name = "CRUD REST APIs In EazyBank" ,
     description = "To CREATE,UPDATE,FETCH AND DELETE account details")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class AccountsController {
    private IAccountsService iAccountsService;
    @Operation(
            summary = "Create Account REST API",
            description = "REST API To create new Customer & Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<@NonNull ResponseDto>createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201,MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API To Fetch Customer & Account Details based on a Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/fetch")
    public ResponseEntity<@NonNull CustomerDto>fetchAccountDetails(@RequestParam
                                                                       @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                       String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API To Update Customer & Account Details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<@NonNull ResponseDto>updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
           boolean isUpdated = iAccountsService.updateAccount(customerDto);
           if (isUpdated){
               return ResponseEntity.status(HttpStatus.OK)
                       .body(new ResponseDto(STATUS_200,MESSAGE_200));
           } else {
               return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                       .body(new ResponseDto(STATUS_417,MESSAGE_417_UPDATE));
           }
    }
    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API To Delete Customer & Account Details based on a Mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<@NonNull ResponseDto>deleteAccountDetails(@RequestParam
                                                                        @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                        String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200,MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417,MESSAGE_417_DELETE));
        }
    }
}
