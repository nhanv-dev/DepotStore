import React, { useState } from 'react';
import Helmet from "../../../components/common/helmet";
import Logo from "../../../assets/images/logo.png";
import { Link, Navigate } from "react-router-dom";
import { UilAt, UilExclamationTriangle, UilEyeSlash, UilKeyholeCircle } from '@iconscout/react-unicons'
import { login } from "../../../redux/actions/userActions";
import { useDispatch, useSelector } from "react-redux";
import userService from "../../../service/UserService";
import * as Icon from "@iconscout/react-unicons";

function SignIn() {
    const dispatch = useDispatch();
    const user = useSelector(state => state.user);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);

    async function handleSubmit(e) {
        e.preventDefault();
        const action = await login({ email, password });
        if (action?.error === 'Email invalid') {
            setError(prev => ({ ...prev, email: 'Địa chỉ email không đúng' }))
            return;
        }
        if (action?.error === 'Password invalid') {
            setError(prev => ({ ...prev, password: 'Mật khẩu không đúng' }))
            return;
        }
        dispatch(action);
        const data = await userService.prepare(action);
        dispatch(data?.cart)
        dispatch(data?.shop)
    }


    return user?.token ? <Navigate to={"/trang-chu"} /> : (
        <Helmet title="Depot - Đăng nhập">
            <div className="relative w-[100vw] h-[100vh] bg-cover bg-center bg-no-repeat" style={{ backgroundImage: `url("https://i.pinimg.com/736x/cc/da/08/ccda08bf6e2843ec4b3eda9296d83d3f.jpg")` }}>
                <div className="fixed left-0 top-0 w-[100vw] h-[100vh] bg-[black] opacity-30 z-10" />
                <div className="z-50 fixed top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%] flex items-center bg-white p-3 rounded-lg h-[72%]" style={{ boxShadow: "rgba(0, 0, 0, 0.35) 0px 5px 15px" }}>
                    <div className='relative w-[600px] h-full bg-cover bg-center bg-no-repeat rounded-lg' style={{ backgroundImage: `url("https://i.pinimg.com/736x/cc/da/08/ccda08bf6e2843ec4b3eda9296d83d3f.jpg")` }}>
                        <div className='absolute left-6 bottom-6 text-white'>
                            <h5 className='font-bold text-[3rem] mb-5 leading-[3.5rem] capitalize'>Get <br />everything <br />you want</h5>
                            <p className='text-sm font-medium text-white/80'>You can get everything you want if you work hard.<br />Trust the process, and stich to the plan.</p>
                        </div>
                        <div className='absolute left-3 top-3 text-white flex justify-between'>
                            <Link to="/trang-chu" className='rounded-full py-1.5 pl-2 pr-4 text-[.9rem] font-semibold bg-black/20 flex items-center justify-center gap-1'>
                                <Icon.UilAngleLeft className="w-[22px] h-[22px] min-w-[22px] min-h-[22px]" />
                                Back to Website
                            </Link>
                        </div>
                    </div>
                    <div className="p-6 w-full md:w-[580px] bg-white" >
                        <div className="mb-8">
                            <Link to="/trang-chu">
                                <img src={Logo} alt="logo" className="h-[35px] mx-auto" />
                            </Link>
                        </div>
                        <form onSubmit={handleSubmit}>
                            <div className="mb-6">
                                <label htmlFor="email" className="block font-semibold text-[0.95rem] text-black mb-2">
                                    Email
                                </label>
                                <div className={`flex items-center px-4 rounded-md h-[46px] bg-[#f5f6f7]`}>
                                    <div className="flex items-center gap-3 w-full">
                                        <div className="flex items-center justify-center w-[18px] h-[18px]">
                                            <UilAt className="w-full h-full text-black" />
                                        </div>
                                        <input id="email" type="email" value={email}
                                            onChange={(e) => {
                                                setError(prev => ({ ...prev, email: null, password: null }))
                                                setEmail(e.target.value)
                                            }}
                                            className="flex-1 focus:outline-none text-md font-medium text-black bg-[transparent]"
                                        />
                                    </div>
                                </div>
                                {error?.email &&
                                    <div className="h-[22px] text-danger font-semibold text-tiny mt-2">
                                        <p className="flex items-center gap-3">
                                            <UilExclamationTriangle className={"w-[18px] h-[18px]"} />
                                            {error?.email}
                                        </p>
                                    </div>
                                }
                            </div>
                            <div className="mb-5">
                                <label htmlFor="password" className="block font-semibold text-[0.95rem] text-black mb-2">
                                    Mật khẩu
                                </label>
                                <div className={`flex items-center px-4 rounded-md h-[46px] bg-[#f5f6f7]`}>
                                    <div className="flex items-center gap-3 w-full">
                                        <div className="flex items-center justify-center w-[18px] h-[18px]">
                                            <UilKeyholeCircle className="w-full h-full text-black" />
                                        </div>
                                        <input id="password" type="password" value={password}
                                            onChange={(e) => {
                                                setError(prev => ({ ...prev, password: null }))
                                                setPassword(e.target.value)
                                            }}
                                            className="flex-1 focus:outline-none text-md font-medium text-black bg-[transparent]"
                                        />
                                        <button className="flex items-center justify-center w-[18px] h-[18px]">
                                            <UilEyeSlash className="w-full h-full text-black" />
                                        </button>
                                    </div>
                                </div>
                                {error?.password &&
                                    <div className="h-[22px] text-danger font-semibold text-tiny mt-2">
                                        <p className="flex items-center gap-3">
                                            <UilExclamationTriangle className={"w-[18px] h-[18px]"} />
                                            {error?.password}
                                        </p>
                                    </div>
                                }
                            </div>
                            <div className="mb-5">
                                <div className="flex justify-end items-center">
                                    <Link to="/quen-mat-khau"
                                        className="font-medium text-tiny text-black">
                                        Quên mật khẩu ?
                                    </Link>
                                </div>
                            </div>
                            <div className="w-full mb-8">
                                <button type={"submit"}
                                    className="w-full h-[46px] rounded-md text-md font-semibold text-white bg-black">
                                    Đăng nhập
                                </button>
                            </div>
                        </form>
                        <div className="mb-10">
                            <div className="flex items-center justify-center gap-5 mb-8">
                                <span className="flex-1 bg-black opacity-10 h-[2px]"></span>
                                <span className="font-medium text-black text-md">
                                    đăng nhập với
                                </span>
                                <span className="flex-1 bg-black opacity-10 h-[2px]"></span>
                            </div>
                            <div className="flex items-center justify-center gap-5">
                                <button
                                    className="rounded-md bg-[#f5f6f7] w-[42px] h-[42px] flex items-center justify-center">
                                    <img className="w-[20px]"
                                        src="images/google-logo.png"
                                        alt="google" />
                                </button>
                                <button
                                    className="rounded-md bg-[#f5f6f7] w-[42px] h-[42px] flex items-center justify-center">
                                    <img className="w-[20px]"
                                        src="images/apple-logo.png"
                                        alt="apple" />
                                </button>
                                <button
                                    className="rounded-md bg-[#f5f6f7] w-[42px] h-[42px] flex items-center justify-center">
                                    <img className="w-[20px]"
                                        src="images/facebook-logo.png"
                                        alt="facebook" />
                                </button>
                            </div>
                        </div>
                        <div className="mb-0">
                            <div className="flex justify-center items-center gap-1">
                                <span className="font-medium text-tiny text-black">Bạn chưa có tài khoản? </span>
                                <Link to="/dang-ky"
                                    className="font-medium text-tiny text-primary">
                                    Đăng ký
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Helmet>
    );
}

export default SignIn;