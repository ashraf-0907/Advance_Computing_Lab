#include <bits/stdc++.h>

using namespace std;

class LCSMEM
{  
public:
    int **t;
    void initilization(int n,int m)
    {
        t=new int*[n];
        for(int i=0;i<n;i++)
        {
            t[i]=new int[m];
            for(int j=0;j<m;j++)
            {
                t[i][j]=-1;
            }
        }
    }
    int algorithm(string s1,string s2,int n,int m,string &ans)
    {
        if(n==0 || m==0)
        return 0;
        if(t[n][m]!= -1)
        return t[n][m];
        if(s1[n-1]==s2[m-1])
        {
           // ans+=s1[n-1];
          return  t[n][m] = 1+algorithm(s1,s2,n-1,m-1,ans);
        }
        else
        {
          return  t[n][m] = max(algorithm(s1,s2,n-1,m,ans),algorithm(s1,s2,n,m-1,ans));
        }
    }
};
int main()
{
    string s1;
    string s2;
    int lns=0;
    string ans="";
    cout<<"Enter the first string: ";
    cin>>s1;
    cout<<"Enter the second string: ";
    cin>>s2;
    int n=s1.length();
    int m=s2.length();
    LCSMEM *l=new LCSMEM();
    l->initilization(n+1,m+1);
    lns=l->algorithm(s1,s2,n,m,ans);
    cout<<"ANS lenght:- "<<lns<<endl;
    cout<<"ANS string:- "<<ans;
    return 0;
}