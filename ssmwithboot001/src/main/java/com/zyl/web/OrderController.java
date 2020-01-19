package com.zyl.web;

import com.zyl.po.OrderEntity;
import com.zyl.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("restful/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * @描述: 根据ID查询<br>
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OrderEntity> queryOrderById(@PathVariable("id") Long id) {
        try {
            System.out.println("ids==="+id);
            OrderEntity entity = orderService.queryOrderById(id);
            if (null == entity) {
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 200
            // return ResponseEntity.status(HttpStatus.OK).body(entity);
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * @描述: 新增<br>
     * @param entity
     * @return
     */
    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveOrder(OrderEntity entity) {
        try {
            orderService.saveOrder(entity);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * @描述: 修改<br>
     * @param entity
     * @return
     */
    @PutMapping
    //@RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateOrder(OrderEntity entity) {
        try {
            orderService.updateOrder(entity);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * @描述: 删除<br>
     * @param id
     * @return
     */
    @DeleteMapping
    //@RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrder(@RequestParam(value = "id") Long id) {
        try {
            OrderEntity entity = orderService.queryOrderById(id);
            if (null == entity) {
                // 不存在返回404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            orderService.deleteOrderById(id);
            // 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "getHome2")
    public String getHome(){
        logger.info(" order home 打印了 ");
        return "home";
    }
}
