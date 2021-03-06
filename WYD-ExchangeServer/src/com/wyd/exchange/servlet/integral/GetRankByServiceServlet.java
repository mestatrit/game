package com.wyd.exchange.servlet.integral;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import com.wyd.exchange.bean.InteRank;
import com.wyd.exchange.server.factory.ServiceManager;
import com.wyd.exchange.utils.CryptionUtil;

/**
 * 获取挑战赛积分列表
 * @author zguoqiu
 */
public class GetRankByServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1911747458628093909L;

    // /private static final String CONTENT_TYPE = "text/html";
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	byte[] data = CryptionUtil.inputStream2byte(req.getInputStream());
            String dataString = new String(CryptionUtil.Decrypt(data, ServiceManager.getManager().getConfiguration().getString("deckey")), "utf-8");
            int serviceId = Integer.parseInt(dataString);
            int goupId = ServiceManager.getManager().getIntegralService().getGroupIdByServiceId(serviceId);
            List<InteRank> integralList = ServiceManager.getManager().getIntegralService().getRankInfoByService(goupId, serviceId);
            JSONArray jsonObject = JSONArray.fromObject(integralList);
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(200);
            ServletOutputStream out = resp.getOutputStream();
            OutputStreamWriter os = new OutputStreamWriter(out, "utf-8");
            os.write(jsonObject.toString());
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
