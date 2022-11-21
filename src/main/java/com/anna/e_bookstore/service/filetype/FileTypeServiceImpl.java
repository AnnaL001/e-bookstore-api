package com.anna.e_bookstore.service.filetype;

import com.anna.e_bookstore.dao.filetype.PsqlFileTypeDao;
import com.anna.e_bookstore.model.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FileTypeServiceImpl implements FileTypeService {
  private final PsqlFileTypeDao fileTypeDao;

  @Autowired
  public FileTypeServiceImpl(PsqlFileTypeDao fileTypeDao){
    this.fileTypeDao = fileTypeDao;
  }

  @Override
  public FileType get(Long fileTypeId) {
    return fileTypeDao.get(fileTypeId);
  }
}
