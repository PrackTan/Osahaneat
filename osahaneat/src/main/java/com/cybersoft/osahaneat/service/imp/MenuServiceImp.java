package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.payload.Request.MenuRequest;
import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImp {
    boolean insertMenu(MenuRequest menuRequest, MultipartFile file);

}
