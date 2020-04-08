// HelloWindowsDesktop.cpp
// compile with: /D_UNICODE /DUNICODE /DWIN32 /D_WINDOWS /c

#include <windows.h>
#include <stdlib.h>
#include <string.h>
#include <tchar.h>
#include <process.h>
#include <string>

// Global variables
#define SIZE_X 500
#define SIZE_Y 500
#define ID_BUTTON_1 501

HWND hWnd;
HWND g_hButton1;
HWND label;
HWND textField;

LPSTR textFieldBufor;
LPSTR  readFromTextField(HWND);
void freeTextFieldBufor();

void drawRectangle();

void __cdecl exampleThread(void* arg) {
    SetWindowText(label, (LPCWSTR)readFromTextField(textField));
    freeTextFieldBufor();
    drawRectangle();
    _endthread();
}

// The main window class name.
static TCHAR szWindowClass[] = _T("DesktopApp");

// The string that appears in the application's title bar.
static TCHAR szTitle[] = _T("Windows Desktop Guided Tour Application");

HINSTANCE hInst;

// Forward declarations of functions included in this code module:
LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);

int CALLBACK WinMain(
    _In_ HINSTANCE hInstance,
    _In_opt_ HINSTANCE hPrevInstance,
    _In_ LPSTR     lpCmdLine,
    _In_ int       nCmdShow
)
{
    WNDCLASSEX wcex;

    wcex.cbSize = sizeof(WNDCLASSEX);
    wcex.style = CS_HREDRAW | CS_VREDRAW;
    wcex.lpfnWndProc = WndProc;
    wcex.cbClsExtra = 0;
    wcex.cbWndExtra = 0;
    wcex.hInstance = hInstance;
    wcex.hIcon = LoadIcon(hInstance, IDI_APPLICATION);
    wcex.hCursor = LoadCursor(NULL, IDC_ARROW);
    wcex.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
    wcex.lpszMenuName = NULL;
    wcex.lpszClassName = szWindowClass;
    wcex.hIconSm = LoadIcon(wcex.hInstance, IDI_APPLICATION);

    if (!RegisterClassEx(&wcex)) {
        MessageBox(NULL,_T("Call to RegisterClassEx failed!"), _T("Windows Desktop Guided Tour"), NULL);
        return 1;
    }

    // Store instance handle in our global variable
    hInst = hInstance;

    // The parameters to CreateWindow explained:
    // szWindowClass: the name of the application
    // szTitle: the text that appears in the title bar
    // WS_OVERLAPPEDWINDOW: the type of window to create
    // CW_USEDEFAULT, CW_USEDEFAULT: initial position (x, y)
    // 500, 100: initial size (width, length)
    // NULL: the parent of this window
    // NULL: this application does not have a menu bar
    // hInstance: the first parameter from WinMain
    // NULL: not used in this application
    hWnd = CreateWindow(szWindowClass, szTitle, WS_OVERLAPPEDWINDOW, CW_USEDEFAULT, CW_USEDEFAULT,
        SIZE_X, SIZE_Y, NULL, NULL, hInstance, NULL);

    if (!hWnd) {
        MessageBox(NULL, _T("Call to CreateWindow failed!"), _T("Windows Desktop Guided Tour"), NULL);
        return 1;
    }

    g_hButton1 = CreateWindowEx(0, L"BUTTON", L"Button", WS_CHILD | WS_VISIBLE,
        10, 10, 150, 30, hWnd, (HMENU)ID_BUTTON_1, hInstance, NULL);

    label = CreateWindowEx(0, L"STATIC", L"Label", WS_CHILD | WS_VISIBLE |
        SS_LEFT, 10, 50, 150, 30, hWnd, NULL, hInstance, NULL);

    textField = CreateWindowEx(WS_EX_CLIENTEDGE, L"EDIT", NULL, WS_CHILD | WS_VISIBLE | WS_BORDER,
        10, 90, 150, 30, hWnd, NULL, hInstance, NULL);

    // The parameters to ShowWindow explained:
    // hWnd: the value returned from CreateWindow
    // nCmdShow: the fourth parameter from WinMain
    ShowWindow(hWnd, nCmdShow);
    UpdateWindow(hWnd);

    // Main message loop:
    MSG msg;
    while (GetMessage(&msg, NULL, 0, 0))
    {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    return (int)msg.wParam;
}

//  FUNCTION: WndProc(HWND, UINT, WPARAM, LPARAM)
//
//  PURPOSE:  Processes messages for the main window.
//
//  WM_PAINT    - Paint the main window
//  WM_DESTROY  - post a quit message and return
LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
    PAINTSTRUCT ps;
    HDC hdc;

    switch (message)
    {
    case WM_PAINT:
        hdc = BeginPaint(hWnd, &ps);
        EndPaint(hWnd, &ps);
        break;
    case WM_DESTROY:
        PostQuitMessage(0);
        break;
    case WM_LBUTTONDOWN: {
        RECT windowPoint;
        GetWindowRect(hWnd, &windowPoint);
        POINT point;
        GetCursorPos(&point);
        int tmp_x = point.x - windowPoint.left - 9;
        int tmp_y = point.y - windowPoint.top - 38;
        SetWindowTextA(label, (LPCSTR((std::to_string(tmp_x) + " " + std::to_string(tmp_y)).c_str())));
        break; 
    }
    case WM_COMMAND:
        switch (wParam) {
        case ID_BUTTON_1:
            _beginthread(exampleThread, 0, NULL);
            break;
        }
    default:
        return DefWindowProc(hWnd, message, wParam, lParam);
        break;
    }
    return 0;
}

LPSTR  readFromTextField(HWND textField) {
    DWORD length = GetWindowTextLength(textField);
    textFieldBufor = (LPSTR)GlobalAlloc(GPTR, length + 1);
    GetWindowText(textField, (LPWSTR)textFieldBufor, length + 1);
    return textFieldBufor;
}

void freeTextFieldBufor() {
    GlobalFree(textFieldBufor);
}

void drawRectangle() {
    int x1;
    int x2;
    int y1;
    int y2;
    HDC hdcWindow = GetDC(hWnd);
    HBRUSH* fulfilment, fulfilmentWrapping;
    fulfilment = new HBRUSH[1];
    HPEN* frame, frameWrapping;
    frame = new HPEN[1];
    fulfilment[0] = CreateSolidBrush(COLORREF(0x000000));
    frame[0] = CreatePen(PS_SOLID, 1, (COLORREF(0x000000)));
    fulfilmentWrapping = (HBRUSH)SelectObject(hdcWindow, fulfilment[0]);
    frameWrapping = (HPEN)SelectObject(hdcWindow, frame[0]);
    x1 = 200;
    x2 = 250;
    y1 = 200;
    y2 = 250;
    Rectangle(hdcWindow, x1, y1, x2, y2);

    DeleteObject(fulfilment);
    DeleteObject(fulfilmentWrapping);
    DeleteObject(frame);
    DeleteObject(frameWrapping);
    ReleaseDC(hWnd, hdcWindow);
    delete[] fulfilment;
    delete[] frame;
}