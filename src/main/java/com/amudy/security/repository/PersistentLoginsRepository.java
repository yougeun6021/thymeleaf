package com.amudy.security.repository;

import com.amudy.security.domain.PersistentLogins;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PersistentLoginsRepository implements PersistentTokenRepository {

    private final JpaPersistentRepository repository;
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        repository.save(PersistentLogins.from(token));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        repository.findBySeries(series)
                .ifPresent(persistentLogins -> persistentLogins.updateToken(tokenValue,lastUsed));
    }

    @Override
    @Transactional(readOnly = true)
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return repository.findBySeries(seriesId)
                .map(persistentLogins ->
                        new PersistentRememberMeToken(
                                persistentLogins.getUsername(),
                                persistentLogins.getSeries(),
                                persistentLogins.getToken(),
                                persistentLogins.getLastUsed()
                        ))
                .orElseThrow(()->new IllegalArgumentException("PersistentLogins not found"));
    }

    @Override
    public void removeUserTokens(String username) {
        repository.deleteAllInBatch(repository.findByUsername(username));
    }
}
