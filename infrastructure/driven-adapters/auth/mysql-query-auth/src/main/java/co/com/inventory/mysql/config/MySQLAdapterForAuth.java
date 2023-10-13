package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.dtos.SalesByBranchDTO;
import co.com.inventory.mysql.config.models.UserMySQL;
import co.com.inventory.mysql.config.repositories.BranchRepository;
import co.com.inventory.mysql.config.repositories.ProductRepository;
import co.com.inventory.mysql.config.repositories.UserRepository;
import co.com.inventory.mysql.config.utils.JwtServiceAuthMysql;
import co.com.inventory.usecase.generic.gateways.DomainUserRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import co.com.inventory.usecase.utils.AuthRequest;
import co.com.inventory.usecase.utils.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.GrantedAuthority;
import reactor.core.publisher.Flux;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MySQLAdapterForAuth implements DomainUserRepository {
    private final ReactiveAuthenticationManager authManager;
    private final JwtServiceAuthMysql jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper mapper;

    private final ProductRepository productRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;

    @Override
    public Mono<User> saveAUser(User user) {
        //String newId = UUID.randomUUID().toString();
        UserMySQL userData = mapper.map(user, UserMySQL.class);
        userData.setUserPassword(passwordEncoder.encode(userData.getUserPassword()));
        //userData.setId(newId);
        System.out.println(userData.getUserId());
        return userRepository.save(userData).thenReturn(mapper.map(userData, User.class))
        .onErrorMap(DataIntegrityViolationException.class, e -> new DataIntegrityViolationException("Error creating user: "+e.getMessage()));

    }

    @Override
    public Mono<User> saveASuper(User user) {

        user.setUserId(UserId.of(UUID.randomUUID().toString()));
        UserMySQL userData = mapper.map(user, UserMySQL.class);
        userData.setUserPassword(passwordEncoder.encode(userData.getUserPassword()));

        System.out.println(userData.getUserId());
        return userRepository.save(userData).thenReturn(mapper.map(userData, User.class))
                .onErrorMap(DataIntegrityViolationException.class, e -> new DataIntegrityViolationException("Error creating user: "+e.getMessage()));

    }



    @Override
    public Mono<AuthResponse> authenticate(AuthRequest request) {
        return authManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .map(auth -> {
                    var userDetails = (UserDetails) auth.getPrincipal();
                    return getAuthResponse(userDetails);
                });
    }

    private AuthResponse getAuthResponse(UserDetails userDetails) {

        var extraClaims = extractAuthorities("roles", userDetails);

        String branchId = "";
        if (userDetails instanceof UserMySQL) {
            branchId = ((UserMySQL) userDetails).getBranchId();
        }
        extraClaims.put("branchId", branchId);

        var jwtToken = jwtService.generateToken(userDetails, extraClaims);
        return AuthResponse.builder()
                .role(extraClaims.get("roles").toString())
                .token(jwtToken)
                .build();
    }

    private Map<String, Object> extractAuthorities(String key, UserDetails userDetails) {
        Map<String, Object> authorities = new HashMap<>();

        authorities.put(key,
                userDetails
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")));

        return authorities;
    }
}

