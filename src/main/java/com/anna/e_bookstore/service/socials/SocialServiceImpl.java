package com.anna.e_bookstore.service.socials;

import com.anna.e_bookstore.dao.socials.PsqlSocialDao;
import com.anna.e_bookstore.model.Socials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SocialServiceImpl implements SocialService{
  private final PsqlSocialDao socialDao;

  @Autowired
  public SocialServiceImpl(PsqlSocialDao socialDao){
    this.socialDao = socialDao;
  }

  @Override
  public void add(Socials socials) {
    socialDao.add(socials);
  }
}
