import { useState } from 'react'

function useMultipartForm(steps) {
    const [currentStepIndex, setcurrentStepIndex] = useState(0)

    function next() {
        setcurrentStepIndex(() => {
            if (currentStepIndex >= steps.length) {
                return currentStepIndex
            } else {
                return currentStepIndex + 1
            }
        })
    }

    function back() {
        setcurrentStepIndex(() => {
            if (currentStepIndex <= 1) {
                return 0
            } else {
                return currentStepIndex - 1
            }
        })
    }

    function goTo(index) {
        setcurrentStepIndex(index)
    }

    return {
        currentStepIndex,
        step: steps[currentStepIndex],
        steps,
        isFirstStep: currentStepIndex === 0,
        isLastStep: currentStepIndex === steps.length - 1,
        goTo,
        next,
        back,
    }
}

export default useMultipartForm