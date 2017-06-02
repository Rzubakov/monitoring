/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;


import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Rzubakov
 */
@Interceptor
@Logger
public class LoggerImp {
    @AroundInvoke
    public Object meter(InvocationContext context) throws Exception {
        System.out.println(context.getMethod()+" "+context.getParameters());
        long startTime=0;
        try {
            startTime=System.currentTimeMillis();
            return context.proceed();
        }finally{
            System.out.println(context.getMethod().getClass()+"."+context.getMethod().getName()+" "+(System.currentTimeMillis()-startTime)+"ms");
        }
    }

}
