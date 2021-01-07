import React, { useCallback, useRef } from 'react';
import Captcha from 'react-captcha-code';

export const ImageCaptcha = () => {
    const handleChange = useCallback((captcha) => {
        console.log('captcha:', captcha);
    }, []);

    const captchaRef = useRef<HTMLCanvasElement>();

    const handleClick = () => {
        // 刷新验证码
        (captchaRef as any).current.refresh();
    };

    return (
        <>
            <Captcha ref={ captchaRef} charNum={6} onChange={handleChange} />
            <div>
                <button onClick={handleClick}>更换验证码</button>
            </div>
        </>
    );
};