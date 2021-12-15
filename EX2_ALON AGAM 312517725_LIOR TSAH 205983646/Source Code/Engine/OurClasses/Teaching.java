package OurClasses;

import genreatedClasses.ETTTeaches;
import genreatedClasses.ETTTeaching;

import java.util.ArrayList;
import java.util.List;

public class Teaching {
    private ETTTeaching m_ETTTeaching;
    private List<Teaches> m_Teaches;


    public Teaching(ETTTeaching ettTeaching) {
        m_ETTTeaching = ettTeaching;
        m_Teaches = new ArrayList<>();
        for (ETTTeaches currentTeaching : ettTeaching.getETTTeaches()) {
            m_Teaches.add(new Teaches(currentTeaching));
        }
    }

    public List<Teaches> getM_Teaches() {
        return m_Teaches;
    }

    public void setM_Teaches(List<Teaches> m_Teaches) {
        this.m_Teaches = m_Teaches;
    }

    public ETTTeaching getM_ETTTeaching() {
        return m_ETTTeaching;
    }

    public void setM_ETTTeaching(ETTTeaching m_ETTTeaching) {
        this.m_ETTTeaching = m_ETTTeaching;
    }
}
