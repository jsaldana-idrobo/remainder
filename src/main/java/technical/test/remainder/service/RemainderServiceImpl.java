package technical.test.remainder.service;

import org.springframework.stereotype.Service;

@Service
public class RemainderServiceImpl implements RemainderService {

    @Override
    public Boolean checkData(int x, int y, int n) {
        boolean validX = x >= 2 && x <= Math.pow(10, 9);
        boolean validY = y >= 0 && y < x;
        boolean validN = n >= y && n <= Math.pow(10, 9);

        return validX && validY && validN;
    }
    @Override
    public Integer calculateRemainder(int x, int y, int n) {
        if (!checkData(x,y,n)) return 0;
        return ((n - y) / x) * x + y;
    }
}