import { request} from '@/request';

interface UserData {
    code: number;
    msg: string;
    data: any;
    // 其他属性
}

interface RegisterDTO {
    email: string;
    username: string;
    password: string;
    avatar?: string;
    bio?: string;
    wechatAccount?: string;
    qqAccount?: string;
    isLoggedOut?: number;
}

interface PageQueryDTO {
    startTime: string;
    endTime: string;
    page: number;
    pageSize: number;
}

export function login(username:string,password:string){
    return request<UserData>({
        url:'/api/user/login',
        method: "post",
        data:{
            username,
            password
        }
    })
}

export function register(registerDTO:RegisterDTO){
    return request<UserData>(
        {
            url:'/api/user/register',
            method: "post",
            data: registerDTO,
        }
    )
}
export function logout(uid:number){
    return request<UserData>(
        {
            url:'/api/user/logout',
            method: "post",
            data: {uid},
        }
    )
}

export function getPageList(pageQueryDTO:PageQueryDTO){
    return request<any>(
        {
            url:'/api/user/page',
            method: "post",
            data: pageQueryDTO,
        }
    )
}
export function edit(username:string, uid: number){
    return request<UserData>(
        {
            url:'/api/user/edit',
            method: "post",
            data: {
                username,
                uid
            }
        }
    )
}
