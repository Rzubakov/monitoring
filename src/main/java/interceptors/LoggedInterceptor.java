/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import java.lang.reflect.Parameter;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Interceptor
public class LoggedInterceptor {

    @AroundInvoke
    public Object meter(InvocationContext context) throws Exception {
        long startTime = 0;
        try {
            startTime = System.currentTimeMillis();
            System.out.println("->Start method: " + context.getMethod().getName() + " " + startTime + " ms");
            return context.proceed();
        } finally {
            System.out.println("->End method: " + context.getMethod().getName() + " " + System.currentTimeMillis() + " ms " + "delta: " + (System.currentTimeMillis() - startTime) + "ms");
        }
    }

}
