import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author le
 */
public class Calc extends HttpServlet {
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
                double result = 0;
                String messageResult;                                   // строка результата  
                String valueOne = request.getParameter("n1"); //получаем первое число для операции
                String valueTwo = request.getParameter("n2"); //получаем второе число для операции
                
                if(!valueOne.isEmpty() && !valueTwo.isEmpty()) {  // проверяем пришедшие значения на наличие
                    double a1= Double.parseDouble(valueOne);  // конвертируем строку в double
                    double a2= Double.parseDouble(valueTwo); 
                    
                    Map parameters = request.getParameterMap(); // получаем map по input c формы, в котором name - ключ, value - значение 
                    if (parameters.containsKey("plus")) {           // проверяем какое действие было выполнено
                        result = a1+a2;
                    } else if (parameters.containsKey("minus")) {
                        result = a1-a2;
                    } else if (parameters.containsKey("multiply")) {
                        result = a1*a2;
                    } else if (parameters.containsKey("divide")) {
                        result = a1/a2;
                    }
                    messageResult = String.valueOf((double) result);            // если все ок, конвертируем результат операции в строку и присваиваем messageResult
                } else {
                   messageResult = "Одно из чисел не было введено. Пожалуйста, попробуйте снова."; // если не введено одно из чисел для вычисления, в messageResult записывается текст ошибки
                }
                response(response, messageResult); // запускаем метод response для вывода формы с результатом
                
	}
    private void response(HttpServletResponse response, String result)
			throws IOException {
                response.setContentType("text/html; charset=UTF-8");  // формат данных, кодировка в ответе
		PrintWriter out = response.getWriter();
		out.println("<html>");
                    out.println("<head>");
                    out.println("<link rel='stylesheet' type='text/css' href='style.css' />");
                    out.println("</head>");
                    out.println("<body>");
                        out.println("<div class='result'><h3 style='text-align:center'> Результат операции: "+ result );
                        out.println("</h3> </div> ");
                        out.println("<a href='/Calculator'><-- вернуться назад</a>");
                    out.println("</body>");
                out.println("</html>");
	}


}
