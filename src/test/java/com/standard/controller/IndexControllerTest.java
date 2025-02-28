package com.standard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {IndexControllerTest.class})
public class IndexControllerTest extends AbstractRestControllerTest {

}
