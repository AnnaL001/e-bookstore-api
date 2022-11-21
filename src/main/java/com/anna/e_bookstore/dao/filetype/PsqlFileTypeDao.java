package com.anna.e_bookstore.dao.filetype;

import com.anna.e_bookstore.model.FileType;

public interface PsqlFileTypeDao {
  FileType get(Long fileTypeId);
}
