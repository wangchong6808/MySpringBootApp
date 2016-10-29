Request

    URL : /appname/lead
    method : POST
    parameters
        user_name=SC_Name
        lead_info={'first_name':'名', 'last_name':'姓', 'gender':'male/female', 'mobile':'13911911999', 'fixed_line_number':'66668888',
    'wechat':'zhang123','company':'Daimler', 'address':'东直门', 'channel':1, 'role':1, 'grade':'A', 'comments': '备注信息'}


Response
    {
        'status_code' : '100'
                               /*
                                   100 success,
                                   201 required field missing
                                   202 duplicate lead (mobile number already exists)
                                   
                               */
        'error': '错误信息'  // exists when statusCode=0
        'response': {'lead_id':'L20161012000001', 'first_name','名', 'last_name':'姓', 'gender':'male/female',
                        'mobile':'13911911999', 'fixed_line_number':'66668888',
                        'wechat':'zhang123','company':'Daimler', 'address':'东直门', 'channel':1,
                        'role':1, 'grade':'A', 'comments': '备注信息', 'version':1}
    }


    channel 枚举值 暂时前端写死
    1 : 散客线索
    2 : 呼入线索
    3 : 网络线索
    4 : 主动集客线索
    5 : 推荐线索
    6 : 再购线索
    7 : 活跃线索
    8 : 休眠线索

    role 枚举值 暂时前前端写死
    1 : 购买人
    2 : 使用人