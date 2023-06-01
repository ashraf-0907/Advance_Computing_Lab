#include <bits/stdc++.h>
using namespace std;

class LCSBU
{
private:
    int **t;

public:
    int algorithm(string s1, string s2, int n, int m,string &ans)
    {
        // int n = s1.length();
        // int m = s2.length();
        int t[n + 1][m + 1];

        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= m; j++)
            {
                if (i == 0 || j == 0)
                    t[i][j] = 0;
                else if (s1[i-1] == s2[j-1])
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = max(t[i - 1][j], t[i][j - 1]);
            }
        }
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=m;j++)
            {
                cout<<t[i][j]<<" ";
            }
            cout<<endl;
        }
        int i = n;
        int j = m;
        while (i > 0 && j > 0)
        {
            if (s1[i - 1] == s2[j - 1])
            {
                ans.push_back(s1[i - 1]);
                i--;
                j--;
                //cout << ans << endl;
            }
            else
            {
                if (t[i - 1][j] < t[i][j - 1])
                    j--;
                else
                    i--;
               // cout<<"else 1";
            }
        }
        reverse(ans.begin(), ans.end());
        return t[n][m];
    }
};
int main()
{
    string s1;
    string s2;
    int lns = 0;
    string ans;
    cout << "Enter the first string: ";
    cin >> s1;
    cout << "Enter the second string: ";
    cin >> s2;
    int n = s1.length();
    int m = s2.length();
    int t[n+1][m+1];
    LCSBU *b = new LCSBU();
    lns = b->algorithm(s1, s2, n, m,ans);
    cout << "Ans length:- " << lns << endl;
   // b->print(n+1,m+1);
    //cout<<n<<m;
    //string ans=(b->printLCS(s1, s2, n, m));
    cout << "Ans string:- " << ans << endl;
    return 0;
}
